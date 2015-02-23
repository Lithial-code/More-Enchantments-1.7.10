package com.lithial.me.handlers.utils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

import org.lwjgl.util.glu.GLU;

import cpw.mods.fml.common.ObfuscationReflectionHelper;

public class OpenGlHelper {
	private Minecraft mc;
	public static Vec3 closestLinePartToPoint(Vec3 start, Vec3 end, Vec3 point) {
		if (end == null || start == null || point == null)
			return null;
		Vec3 u = Vec3.createVectorHelper(end.xCoord - start.xCoord, end.yCoord - start.yCoord, end.zCoord - start.zCoord);
		Vec3 v = Vec3.createVectorHelper(point.xCoord - start.xCoord, point.yCoord - start.yCoord, point.zCoord - start.zCoord);
		u = u.normalize();
		double s = u.dotProduct(v);
		return Vec3.createVectorHelper(start.xCoord + u.xCoord * s, start.yCoord + u.yCoord * s, start.zCoord + u.zCoord * s);
	}

	public static double distanceFromLine(Vec3 start, Vec3 end, Vec3 point) {
		double totalDist = 0;
		Vec3 closestPoint = closestLinePartToPoint(start, end, point);
		if (closestPoint != null) {
			totalDist = Math.sqrt((closestPoint.xCoord - point.xCoord) * (closestPoint.xCoord - point.xCoord) + (closestPoint.yCoord - point.yCoord) * (closestPoint.yCoord - point.yCoord) + (closestPoint.zCoord - point.zCoord) * (closestPoint.zCoord - point.zCoord));
		}
		return totalDist;
	}

	// Calculate the normal to a plane;
	public static Vec3 calculatePlaneNormal(Vec3 plane_p1, Vec3 plane_p2, Vec3 plane_p3) {
		double x1 = plane_p1.xCoord;
		double x2 = plane_p2.xCoord;
		double x3 = plane_p3.xCoord;
		double y1 = plane_p1.yCoord;
		double y2 = plane_p2.yCoord;
		double y3 = plane_p3.yCoord;
		double z1 = plane_p1.zCoord;
		double z2 = plane_p2.zCoord;
		double z3 = plane_p3.zCoord;
		double a = y1 * (z2 - z3) + y2 * (z3 - z1) + y3 * (z1 - z2);
		double b = z1 * (x2 - x3) + z2 * (x3 - x1) + z3 * (x1 - x2);
		double c = x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2);
		return Vec3.createVectorHelper(a, b, c);
	}

	// Check if a line intersects a plane;
	public static Vec3 lineIntersectPlane(Vec3 line_p1, Vec3 line_p2, Vec3 plane_p1, Vec3 plane_p2, Vec3 plane_p3, boolean inLimits) {
		Vec3 lineDir = Vec3.createVectorHelper(line_p2.xCoord - line_p1.xCoord, line_p2.yCoord - line_p1.yCoord, line_p2.zCoord - line_p1.zCoord);
		Vec3 planeNormal = calculatePlaneNormal(plane_p1, plane_p2, plane_p3);
		Vec3 diffPoint = Vec3.createVectorHelper(plane_p1.xCoord - line_p1.xCoord, plane_p1.yCoord - line_p1.yCoord, plane_p1.zCoord - line_p1.zCoord);
		double denom = lineDir.dotProduct(planeNormal);
		if (denom == 0) {
			return null;
		}
		double dist = diffPoint.dotProduct(planeNormal);
		dist /= denom;
		if (inLimits) {

			if (dist < 0 || dist > 1) {
				return null;
			}
		}
 
		return Vec3.createVectorHelper(line_p1.xCoord + (line_p2.xCoord - line_p1.xCoord) * dist, line_p1.yCoord + (line_p2.yCoord - line_p1.yCoord) * dist, line_p1.zCoord + (line_p2.zCoord - line_p1.zCoord) * dist);
	}

	// Check if a line intersects a quad;
	public static Vec3 lineIntersectsQuad(Vec3 line_p1, Vec3 line_p2, Vec3 sq_p1, Vec3 sq_p2, Vec3 sq_p3, Vec3 sq_p4) {
		Vec3 intersectionPoint = lineIntersectPlane(line_p1, line_p2, sq_p1, sq_p2, sq_p3, true);
		Vec3[] otherPoints = { sq_p2, sq_p3, sq_p4 };

		if (intersectionPoint == null) {

			return null;

		}

		double maxDistX = 0;
		double maxDistY = 0;
		double maxDistZ = 0;
		for (Vec3 altPoint : otherPoints) {
			double p_maxDistX = Math.abs(sq_p1.xCoord - altPoint.xCoord);
			double p_maxDistY = Math.abs(sq_p1.yCoord - altPoint.yCoord);
			double p_maxDistZ = Math.abs(sq_p1.zCoord - altPoint.zCoord);
			if (maxDistX < p_maxDistX) {
				maxDistX = p_maxDistX;
			}
			if (maxDistY < p_maxDistY) {
				maxDistY = p_maxDistY;
			}
			if (maxDistZ < p_maxDistZ) {
				maxDistZ = p_maxDistZ;
			}
		}

		for (Vec3 p : otherPoints) {
			double diffX = Math.abs(p.xCoord - intersectionPoint.xCoord);
			double diffY = Math.abs(p.yCoord - intersectionPoint.yCoord);
			double diffZ = Math.abs(p.zCoord - intersectionPoint.zCoord);
			if (diffX > maxDistX || diffY > maxDistY || diffZ > maxDistZ) {
				return null;
			}

		}
		return intersectionPoint;
	}

	public static Vec3 lineIntersectionEntity(Vec3 line_p1, Vec3 line_p2, Entity entity) {
		// Check each of the quads in turn;

	 
		AxisAlignedBB cb = entity.boundingBox;
		// front
		Vec3 side1 = Vec3.createVectorHelper(cb.maxX, cb.maxY, cb.minZ);
		Vec3 side2 = Vec3.createVectorHelper(cb.maxX, cb.minY, cb.minZ);
		Vec3 side3 = Vec3.createVectorHelper(cb.minX, cb.minY, cb.minZ);
		Vec3 side4 = Vec3.createVectorHelper(cb.minX, cb.maxY, cb.minZ);
		Vec3 result = lineIntersectsQuad(line_p1, line_p2, side1, side2, side3, side4);
		if (result != null)
			return result;

		// Left
		side1 = Vec3.createVectorHelper(cb.minX, cb.maxY, cb.maxZ);
		side2 = Vec3.createVectorHelper(cb.minX, cb.minY, cb.maxZ);
		side3 = Vec3.createVectorHelper(cb.minX, cb.minY, cb.minZ);
		side4 = Vec3.createVectorHelper(cb.minX, cb.maxY, cb.minZ);
		result = lineIntersectsQuad(line_p1, line_p2, side1, side2, side3, side4);
		if (result != null)
			return result;
		// Back
		side1 = Vec3.createVectorHelper(cb.maxX, cb.maxY, cb.maxZ);
		side2 = Vec3.createVectorHelper(cb.maxX, cb.minY, cb.maxZ);
		side3 = Vec3.createVectorHelper(cb.minX, cb.minY, cb.maxZ);
		side4 = Vec3.createVectorHelper(cb.minX, cb.maxY, cb.maxZ);

		result = lineIntersectsQuad(line_p1, line_p2, side1, side2, side3, side4);
		if (result != null)
			return result;
		// right
		side1 = Vec3.createVectorHelper(cb.maxX, cb.maxY, cb.maxZ);
		side2 = Vec3.createVectorHelper(cb.maxX, cb.minY, cb.maxZ);
		side3 = Vec3.createVectorHelper(cb.maxX, cb.minY, cb.minZ);
		side4 = Vec3.createVectorHelper(cb.maxX, cb.maxY, cb.minZ);
		result = lineIntersectsQuad(line_p1, line_p2, side1, side2, side3, side4);
		if (result != null)
			return result;
		// Top
		side1 = Vec3.createVectorHelper(cb.maxX, cb.maxY, cb.maxZ);
		side2 = Vec3.createVectorHelper(cb.maxX, cb.maxY, cb.minZ);
		side3 = Vec3.createVectorHelper(cb.minX, cb.maxY, cb.minZ);
		side4 = Vec3.createVectorHelper(cb.minX, cb.maxY, cb.maxZ);
		result = lineIntersectsQuad(line_p1, line_p2, side1, side2, side3, side4);
		if (result != null)
			return result;
		return null;

	}

	public static double vecMagDiff(Vec3 p1, Vec3 p2) {
		return Math.sqrt((p1.xCoord - p2.xCoord) * (p1.xCoord - p2.xCoord) + (p1.yCoord - p2.yCoord) * (p1.yCoord - p2.yCoord) + (p1.zCoord - p2.zCoord) * (p1.zCoord - p2.zCoord));
	}

	public double approxDistAlongLine(Vec3 start, Vec3 end, Vec3 point) {
		Vec3 closestPoint = closestLinePartToPoint(start, end, point);
		double dist = 0;
		if (closestPoint != null) {
			if (Math.abs(end.xCoord - start.xCoord) > dist) {
				dist = Math.abs(end.xCoord - start.xCoord);
			}
			if (Math.abs(end.yCoord - start.yCoord) > dist) {
				dist = Math.abs(end.yCoord - start.yCoord);
			}
			if (Math.abs(end.zCoord - start.zCoord) > dist) {
				dist = Math.abs(end.zCoord - start.zCoord);
			}
		}
		return dist;
	}
 
	 public Vec3 screenCoordsFrom3D(float x, float y, float z) {

		FloatBuffer screen_coords = GLAllocation.createDirectFloatBuffer(4);
		IntBuffer viewport;
		FloatBuffer modelview;
		FloatBuffer projection;
		try {
			viewport = (IntBuffer) ObfuscationReflectionHelper.getPrivateValue(ActiveRenderInfo.class, new ActiveRenderInfo(), "viewport");
			modelview = (FloatBuffer) ObfuscationReflectionHelper.getPrivateValue(ActiveRenderInfo.class, new ActiveRenderInfo(), "modelview");
			projection = (FloatBuffer) ObfuscationReflectionHelper.getPrivateValue(ActiveRenderInfo.class, new ActiveRenderInfo(), "projection");
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
 
		boolean result = GLU.gluProject(x, y, z, modelview, projection, viewport, screen_coords);

		if (result) {
 			System.out.printf("Convert [ %6.2f %6.2f %6.2f ] -> Screen [ %4d %4d ]\n", x, y, z, (int) screen_coords.get(0), mc.displayHeight - (int) (screen_coords.get(1)));
 		return null;
		} else {
			System.out.printf("Failed to convert 3D coords to 2D screen coords");
			return null;
		}
	}

}
