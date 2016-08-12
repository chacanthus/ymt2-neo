// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.math;

import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;
import java.util.Arrays;
import java.util.List;

public final class Intersector {
    public class MinimumTranslationVector {
        public MinimumTranslationVector() {
            super();
            this.normal = new Vector2();
            this.depth = 0f;
        }
    }

    public class SplitTriangle {
        public float[] back;
        int backOffset;
        float[] edgeSplit;
        public float[] front;
        boolean frontCurrent;
        int frontOffset;
        public int numBack;
        public int numFront;
        public int total;

        public SplitTriangle(int numAttributes) {
            super();
            this.frontCurrent = false;
            this.frontOffset = 0;
            this.backOffset = 0;
            this.front = new float[numAttributes * 3 * 2];
            this.back = new float[numAttributes * 3 * 2];
            this.edgeSplit = new float[numAttributes];
        }

        void add(float[] vertex, int offset, int stride) {
            if(this.frontCurrent) {
                System.arraycopy(vertex, offset, this.front, this.frontOffset, stride);
                this.frontOffset += stride;
            }
            else {
                System.arraycopy(vertex, offset, this.back, this.backOffset, stride);
                this.backOffset += stride;
            }
        }

        boolean getSide() {
            return this.frontCurrent;
        }

        void reset() {
            this.frontCurrent = false;
            this.frontOffset = 0;
            this.backOffset = 0;
            this.numFront = 0;
            this.numBack = 0;
            this.total = 0;
        }

        void setSide(boolean front) {
            this.frontCurrent = front;
        }

        public String toString() {
            return "SplitTriangle [front=" + Arrays.toString(this.front) + ", back=" + Arrays.toString(this.back) + ", numFront=" + this.numFront + ", numBack=" + this.numBack + ", total=" + this.total + "]";
        }
    }

    static Vector3 best;
    private static final Vector3 dir;
    private static final Vector3 i;
    static Vector3 intersection;
    private static final Plane p;
    private static final Vector3 start;
    static Vector3 tmp;
    static Vector3 tmp1;
    static Vector3 tmp2;
    static Vector3 tmp3;
    private static final Vector3 v0;
    private static final Vector3 v1;
    private static final Vector3 v2;
    static Vector2 v2tmp;

    static  {
        Intersector.v0 = new Vector3();
        Intersector.v1 = new Vector3();
        Intersector.v2 = new Vector3();
        Intersector.p = new Plane(new Vector3(), 0f);
        Intersector.i = new Vector3();
        Intersector.dir = new Vector3();
        Intersector.start = new Vector3();
        Intersector.best = new Vector3();
        Intersector.tmp = new Vector3();
        Intersector.tmp1 = new Vector3();
        Intersector.tmp2 = new Vector3();
        Intersector.tmp3 = new Vector3();
        Intersector.v2tmp = new Vector2();
        Intersector.intersection = new Vector3();
    }

    public Intersector() {
        super();
    }

    static float det(float a, float b, float c, float d) {
        return a * d - b * c;
    }

    static double detd(double a, double b, double c, double d) {
        return a * d - b * c;
    }

    public static float distanceLinePoint(float startX, float startY, float endX, float endY, float pointX, float pointY) {
        return Math.abs((pointX - startX) * (endY - startY) - (pointY - startY) * (endX - startX)) / (((float)Math.sqrt(((double)((endX - startX) * (endX - startX) + (endY - startY) * (endY - startY))))));
    }

    public static float distanceLinePoint(Vector2 start, Vector2 end, Vector2 point) {
        float v2;
        Intersector.tmp.set(end.x, end.y, 0f);
        float v0 = Intersector.tmp.sub(start.x, start.y, 0f).len2();
        if(v0 == 0f) {
            v2 = point.dst(start);
        }
        else {
            Intersector.tmp.set(point.x, point.y, 0f);
            Intersector.tmp.sub(start.x, start.y, 0f);
            Intersector.tmp2.set(end.x, end.y, 0f);
            Intersector.tmp2.sub(start.x, start.y, 0f);
            float v1 = Intersector.tmp.dot(Intersector.tmp2) / v0;
            if(v1 < 0f) {
                v2 = point.dst(start);
            }
            else if(v1 > 1f) {
                v2 = point.dst(end);
            }
            else {
                Intersector.tmp.set(end.x, end.y, 0f);
                Intersector.tmp.sub(start.x, start.y, 0f).scl(v1).add(start.x, start.y, 0f);
                v2 = Intersector.tmp2.set(point.x, point.y, 0f).dst(Intersector.tmp);
            }
        }

        return v2;
    }

    public static float distanceSegmentPoint(float startX, float startY, float endX, float endY, float pointX, float pointY) {
        return Intersector.nearestSegmentPoint(startX, startY, endX, endY, pointX, pointY, Intersector.v2tmp).dst(pointX, pointY);
    }

    public static float distanceSegmentPoint(Vector2 start, Vector2 end, Vector2 point) {
        return Intersector.nearestSegmentPoint(start, end, point, Intersector.v2tmp).dst(point);
    }

    public static float intersectLinePlane(float x, float y, float z, float x2, float y2, float z2, Plane plane, Vector3 intersection) {
        float v3;
        Vector3 v1 = Intersector.tmp.set(x2, y2, z2).sub(x, y, z);
        Vector3 v2 = Intersector.tmp2.set(x, y, z);
        float v0 = v1.dot(plane.getNormal());
        if(v0 != 0f) {
            v3 = -(v2.dot(plane.getNormal()) + plane.getD()) / v0;
            if(v3 >= 0f && v3 <= 1f && intersection != null) {
                intersection.set(v2).add(v1.scl(v3));
            }
        }
        else if(plane.testPoint(v2) == PlaneSide.OnPlane) {
            if(intersection != null) {
                intersection.set(v2);
            }

            v3 = 0f;
        }
        else {
            v3 = -1f;
        }

        return v3;
    }

    public static boolean intersectLinePolygon(Vector2 p1, Vector2 p2, Polygon polygon) {
        boolean v21;
        float[] v8 = polygon.getTransformedVertices();
        float v12 = p1.x;
        float v17 = p1.y;
        float v13 = p2.x;
        float v9 = v12 - v13;
        float v4 = v17 - p2.y;
        float v1 = v12 * p2.y - v17 * v13;
        int v7 = v8.length;
        float v14 = v8[v7 - 2];
        float v19 = v8[v7 - 1];
        int v6;
        for(v6 = 0; v6 < v7; v6 += 2) {
            float v15 = v8[v6];
            float v20 = v8[v6 + 1];
            float v2 = v14 * v20 - v19 * v15;
            float v10 = v14 - v15;
            float v5 = v19 - v20;
            float v3 = v9 * v5 - v4 * v10;
            float v11 = (v1 * v10 - v9 * v2) / v3;
            if(v11 >= v14 && v11 <= v15) {
                goto label_42;
            }
            else if(v11 >= v15 && v11 <= v14) {
            label_42:
                float v16 = (v1 * v5 - v4 * v2) / v3;
                if(v16 < v19 || v16 > v20) {
                    if(v16 >= v20) {
                        if(v16 <= v19) {
                            goto label_50;
                        }

                        goto label_52;
                    }
                    else {
                        goto label_52;
                    }
                }

            label_50:
                v21 = true;
                goto label_51;
            }

        label_52:
            v14 = v15;
            v19 = v20;
        }

        v21 = false;
    label_51:
        return v21;
    }

    public static boolean intersectLines(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, Vector2 intersection) {
        boolean v2;
        float v0 = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        if(v0 == 0f) {
            v2 = false;
        }
        else {
            if(intersection != null) {
                float v1 = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / v0;
                intersection.set((x2 - x1) * v1 + x1, (y2 - y1) * v1 + y1);
            }

            v2 = true;
        }

        return v2;
    }

    public static boolean intersectLines(Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4, Vector2 intersection) {
        boolean v11;
        float v3 = p1.x;
        float v7 = p1.y;
        float v4 = p2.x;
        float v8 = p2.y;
        float v5 = p3.x;
        float v9 = p3.y;
        float v6 = p4.x;
        float v10 = p4.y;
        float v1 = (v10 - v9) * (v4 - v3) - (v6 - v5) * (v8 - v7);
        if(v1 == 0f) {
            v11 = false;
        }
        else {
            if(intersection != null) {
                float v2 = ((v6 - v5) * (v7 - v9) - (v10 - v9) * (v3 - v5)) / v1;
                intersection.set((v4 - v3) * v2 + v3, (v8 - v7) * v2 + v7);
            }

            v11 = true;
        }

        return v11;
    }

    public static boolean intersectRayBounds(Ray ray, BoundingBox box, Vector3 intersection) {
        boolean v0;
        float v2;
        Vector3.tmp.set(ray.origin);
        Vector3.tmp2.set(ray.origin);
        Vector3.tmp.sub(box.min);
        Vector3.tmp2.sub(box.max);
        if(Vector3.tmp.x <= 0f || Vector3.tmp.y <= 0f || Vector3.tmp.z <= 0f || Vector3.tmp2.x >= 0f || Vector3.tmp2.y >= 0f || Vector3.tmp2.z >= 0f) {
            float v1 = 0f;
            v0 = false;
            if(ray.origin.x <= box.min.x && ray.direction.x > 0f) {
                v2 = (box.min.x - ray.origin.x) / ray.direction.x;
                if(v2 >= 0f) {
                    Vector3.tmp3.set(ray.direction).scl(v2).add(ray.origin);
                    if(Vector3.tmp3.y >= box.min.y && Vector3.tmp3.y <= box.max.y && Vector3.tmp3.z >= box.min.z && Vector3.tmp3.z <= box.max.z) {
                        if(0 != 0 && v2 >= 0f) {
                            goto label_82;
                        }

                        v0 = true;
                        v1 = v2;
                    }
                }
            }

        label_82:
            if(ray.origin.x >= box.max.x && ray.direction.x < 0f) {
                v2 = (box.max.x - ray.origin.x) / ray.direction.x;
                if(v2 >= 0f) {
                    Vector3.tmp3.set(ray.direction).scl(v2).add(ray.origin);
                    if(Vector3.tmp3.y >= box.min.y && Vector3.tmp3.y <= box.max.y && Vector3.tmp3.z >= box.min.z && Vector3.tmp3.z <= box.max.z) {
                        if((v0) && v2 >= v1) {
                            goto label_129;
                        }

                        v0 = true;
                        v1 = v2;
                    }
                }
            }

        label_129:
            if(ray.origin.y <= box.min.y && ray.direction.y > 0f) {
                v2 = (box.min.y - ray.origin.y) / ray.direction.y;
                if(v2 >= 0f) {
                    Vector3.tmp3.set(ray.direction).scl(v2).add(ray.origin);
                    if(Vector3.tmp3.x >= box.min.x && Vector3.tmp3.x <= box.max.x && Vector3.tmp3.z >= box.min.z && Vector3.tmp3.z <= box.max.z) {
                        if((v0) && v2 >= v1) {
                            goto label_176;
                        }

                        v0 = true;
                        v1 = v2;
                    }
                }
            }

        label_176:
            if(ray.origin.y >= box.max.y && ray.direction.y < 0f) {
                v2 = (box.max.y - ray.origin.y) / ray.direction.y;
                if(v2 >= 0f) {
                    Vector3.tmp3.set(ray.direction).scl(v2).add(ray.origin);
                    if(Vector3.tmp3.x >= box.min.x && Vector3.tmp3.x <= box.max.x && Vector3.tmp3.z >= box.min.z && Vector3.tmp3.z <= box.max.z) {
                        if((v0) && v2 >= v1) {
                            goto label_223;
                        }

                        v0 = true;
                        v1 = v2;
                    }
                }
            }

        label_223:
            if(ray.origin.z <= box.min.y && ray.direction.z > 0f) {
                v2 = (box.min.z - ray.origin.z) / ray.direction.z;
                if(v2 >= 0f) {
                    Vector3.tmp3.set(ray.direction).scl(v2).add(ray.origin);
                    if(Vector3.tmp3.x >= box.min.x && Vector3.tmp3.x <= box.max.x && Vector3.tmp3.y >= box.min.y && Vector3.tmp3.y <= box.max.y) {
                        if((v0) && v2 >= v1) {
                            goto label_270;
                        }

                        v0 = true;
                        v1 = v2;
                    }
                }
            }

        label_270:
            if(ray.origin.z >= box.max.z && ray.direction.z < 0f) {
                v2 = (box.max.z - ray.origin.z) / ray.direction.z;
                if(v2 >= 0f) {
                    Vector3.tmp3.set(ray.direction).scl(v2).add(ray.origin);
                    if(Vector3.tmp3.x >= box.min.x && Vector3.tmp3.x <= box.max.x && Vector3.tmp3.y >= box.min.y && Vector3.tmp3.y <= box.max.y) {
                        if((v0) && v2 >= v1) {
                            goto label_317;
                        }

                        v0 = true;
                        v1 = v2;
                    }
                }
            }

        label_317:
            if(!v0) {
                goto label_32;
            }

            if(intersection == null) {
                goto label_32;
            }

            intersection.set(ray.direction).scl(v1).add(ray.origin);
        }
        else {
            v0 = true;
        }

    label_32:
        return v0;
    }

    public static boolean intersectRayBoundsFast(Ray ray, BoundingBox box) {
        boolean v8;
        float v7;
        float v5;
        float v6;
        float v2 = 1f / ray.direction.x;
        float v3 = 1f / ray.direction.y;
        float v4 = 1f / ray.direction.z;
        float v0 = (box.min.x - ray.origin.x) * v2;
        float v1 = (box.max.x - ray.origin.x) * v2;
        if(v0 < v1) {
            v6 = v0;
            v5 = v1;
        }
        else {
            v6 = v1;
            v5 = v0;
        }

        v0 = (box.min.y - ray.origin.y) * v3;
        v1 = (box.max.y - ray.origin.y) * v3;
        if(v0 > v1) {
            v7 = v0;
            v0 = v1;
            v1 = v7;
        }

        if(v0 > v6) {
            v6 = v0;
        }

        if(v1 < v5) {
            v5 = v1;
        }

        v0 = (box.min.z - ray.origin.z) * v4;
        v1 = (box.max.z - ray.origin.z) * v4;
        if(v0 > v1) {
            v7 = v0;
            v0 = v1;
            v1 = v7;
        }

        if(v0 > v6) {
            v6 = v0;
        }

        if(v1 < v5) {
            v5 = v1;
        }

        if(v5 < 0f || v5 < v6) {
            v8 = false;
        }
        else {
            v8 = true;
        }

        return v8;
    }

    public static boolean intersectRayPlane(Ray ray, Plane plane, Vector3 intersection) {
        boolean v2 = false;
        float v0 = ray.direction.dot(plane.getNormal());
        if(v0 != 0f) {
            float v1 = -(ray.origin.dot(plane.getNormal()) + plane.getD()) / v0;
            if(v1 >= 0f) {
                if(intersection != null) {
                    intersection.set(ray.origin).add(ray.direction.tmp().scl(v1));
                }

                v2 = true;
            }
        }
        else {
            if(plane.testPoint(ray.origin) != PlaneSide.OnPlane) {
                goto label_15;
            }

            if(intersection != null) {
                intersection.set(ray.origin);
            }

            v2 = true;
        }

    label_15:
        return v2;
    }

    public static boolean intersectRaySphere(Ray ray, Vector3 center, float radius, Vector3 intersection) {
        float v4;
        boolean v8;
        Intersector.dir.set(ray.direction).nor();
        Intersector.start.set(ray.origin);
        float v0 = 2f * Intersector.dir.dot(Intersector.start.tmp().sub(center));
        float v1 = Intersector.start.dst2(center) - radius * radius;
        float v2 = v0 * v0 - 4f * v1;
        if(v2 < 0f) {
            v8 = false;
        }
        else {
            float v3 = ((float)Math.sqrt(((double)v2)));
            if(v0 < 0f) {
                v4 = (-v0 - v3) / 2f;
            }
            else {
                v4 = (-v0 + v3) / 2f;
            }

            float v5 = v4 / 1f;
            float v6 = v1 / v4;
            if(v5 > v6) {
                float v7 = v5;
                v5 = v6;
                v6 = v7;
            }

            if(v6 >= 0f) {
                goto label_51;
            }

            v8 = false;
            goto label_25;
        label_51:
            if(v5 >= 0f) {
                goto label_62;
            }

            if(intersection != null) {
                intersection.set(Intersector.start).add(Intersector.dir.tmp().scl(v6));
            }

            v8 = true;
            goto label_25;
        label_62:
            if(intersection != null) {
                intersection.set(Intersector.start).add(Intersector.dir.tmp().scl(v5));
            }

            v8 = true;
        }

    label_25:
        return v8;
    }

    public static boolean intersectRayTriangle(Ray ray, Vector3 t1, Vector3 t2, Vector3 t3, Vector3 intersection) {
        boolean v8;
        Intersector.p.set(t1, t2, t3);
        if(!Intersector.intersectRayPlane(ray, Intersector.p, Intersector.i)) {
            v8 = false;
        }
        else {
            Intersector.v0.set(t3).sub(t1);
            Intersector.v1.set(t2).sub(t1);
            Intersector.v2.set(Intersector.i).sub(t1);
            float v1 = Intersector.v0.dot(Intersector.v0);
            float v2 = Intersector.v0.dot(Intersector.v1);
            float v3 = Intersector.v0.dot(Intersector.v2);
            float v4 = Intersector.v1.dot(Intersector.v1);
            float v5 = Intersector.v1.dot(Intersector.v2);
            float v0 = v1 * v4 - v2 * v2;
            if(v0 == 0f) {
                v8 = false;
            }
            else {
                float v6 = (v4 * v3 - v2 * v5) / v0;
                float v7 = (v1 * v5 - v2 * v3) / v0;
                if(v6 >= 0f && v7 >= 0f && v6 + v7 <= 1f) {
                    if(intersection != null) {
                        intersection.set(Intersector.i);
                    }

                    v8 = true;
                    goto label_7;
                }

                v8 = false;
            }
        }

    label_7:
        return v8;
    }

    public static boolean intersectRayTriangles(Ray ray, List arg11, Vector3 intersection) {
        boolean v5;
        float v3 = 340282346638528860000000000000000000000f;
        int v1 = 0;
        if(arg11.size() % 3 != 0) {
            throw new RuntimeException("triangle list size is not a multiple of 3");
        }

        int v2;
        for(v2 = 0; v2 < arg11.size() - 2; v2 += 3) {
            if(Intersector.intersectRayTriangle(ray, arg11.get(v2), arg11.get(v2 + 1), arg11.get(v2 + 2), Intersector.tmp)) {
                float v0 = ray.origin.tmp().sub(Intersector.tmp).len2();
                if(v0 < v3) {
                    v3 = v0;
                    Intersector.best.set(Intersector.tmp);
                    v1 = 1;
                }
            }
        }

        if(v1 == 0) {
            v5 = false;
        }
        else {
            if(intersection != null) {
                intersection.set(Intersector.best);
            }

            v5 = true;
        }

        return v5;
    }

    public static boolean intersectRayTriangles(Ray ray, float[] triangles, Vector3 intersection) {
        boolean v5 = true;
        float v3 = 340282346638528860000000000000000000000f;
        int v1 = 0;
        if(triangles.length / 3 % 3 != 0) {
            throw new RuntimeException("triangle list size is not a multiple of 3");
        }

        int v2;
        for(v2 = 0; v2 < triangles.length - 6; v2 += 9) {
            if(Intersector.intersectRayTriangle(ray, Intersector.tmp1.set(triangles[v2], triangles[v2 + 1], triangles[v2 + 2]), Intersector.tmp2.set(triangles[v2 + 3], triangles[v2 + 4], triangles[v2 + 5]), Intersector.tmp3.set(triangles[v2 + 6], triangles[v2 + 7], triangles[v2 + 8]), Intersector.tmp)) {
                float v0 = ray.origin.tmp().sub(Intersector.tmp).len2();
                if(v0 < v3) {
                    v3 = v0;
                    Intersector.best.set(Intersector.tmp);
                    v1 = 1;
                }
            }
        }

        if(v1 == 0) {
            v5 = false;
        }
        else if(intersection != null) {
            intersection.set(Intersector.best);
        }

        return v5;
    }

    public static boolean intersectRayTriangles(Ray ray, float[] vertices, short[] indices, int vertexSize, Vector3 intersection) {
        boolean v9;
        float v7 = 340282346638528860000000000000000000000f;
        int v2 = 0;
        if(indices.length % 3 != 0) {
            throw new RuntimeException("triangle list size is not a multiple of 3");
        }

        int v3;
        for(v3 = 0; v3 < indices.length; v3 += 3) {
            int v4 = indices[v3] * vertexSize;
            int v5 = indices[v3 + 1] * vertexSize;
            int v6 = indices[v3 + 2] * vertexSize;
            if(Intersector.intersectRayTriangle(ray, Intersector.tmp1.set(vertices[v4], vertices[v4 + 1], vertices[v4 + 2]), Intersector.tmp2.set(vertices[v5], vertices[v5 + 1], vertices[v5 + 2]), Intersector.tmp3.set(vertices[v6], vertices[v6 + 1], vertices[v6 + 2]), Intersector.tmp)) {
                float v1 = ray.origin.tmp().sub(Intersector.tmp).len2();
                if(v1 < v7) {
                    v7 = v1;
                    Intersector.best.set(Intersector.tmp);
                    v2 = 1;
                }
            }
        }

        if(v2 == 0) {
            v9 = false;
        }
        else {
            if(intersection != null) {
                intersection.set(Intersector.best);
            }

            v9 = true;
        }

        return v9;
    }

    public static boolean intersectRectangles(Rectangle rectangle1, Rectangle rectangle2, Rectangle intersection) {
        boolean v0;
        if(rectangle1.overlaps(rectangle2)) {
            intersection.x = Math.max(rectangle1.x, rectangle2.x);
            intersection.width = Math.min(rectangle1.x + rectangle1.width, rectangle2.x + rectangle2.width) - intersection.x;
            intersection.y = Math.max(rectangle1.y, rectangle2.y);
            intersection.height = Math.min(rectangle1.y + rectangle1.height, rectangle2.y + rectangle2.height) - intersection.y;
            v0 = true;
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public static boolean intersectSegmentCircle(Vector2 start, Vector2 end, Vector2 center, float squareRadius) {
        boolean v4;
        Intersector.tmp.set(end.x - start.x, end.y - start.y, 0f);
        Intersector.tmp1.set(center.x - start.x, center.y - start.y, 0f);
        float v0 = Intersector.tmp.len();
        float v1 = Intersector.tmp1.dot(Intersector.tmp.nor());
        if(v1 <= 0f) {
            Intersector.tmp2.set(start.x, start.y, 0f);
        }
        else if(v1 >= v0) {
            Intersector.tmp2.set(end.x, end.y, 0f);
        }
        else {
            Intersector.tmp3.set(Intersector.tmp.scl(v1));
            Intersector.tmp2.set(Intersector.tmp3.x + start.x, Intersector.tmp3.y + start.y, 0f);
        }

        float v2 = center.x - Intersector.tmp2.x;
        float v3 = center.y - Intersector.tmp2.y;
        if(v2 * v2 + v3 * v3 <= squareRadius) {
            v4 = true;
        }
        else {
            v4 = false;
        }

        return v4;
    }

    public static float intersectSegmentCircleDisplace(Vector2 start, Vector2 end, Vector2 point, float radius, Vector2 displacement) {
        float v2 = Infinityf;
        float v1 = (point.x - start.x) * (end.x - start.x) + (point.y - start.y) * (end.y - start.y);
        float v0 = start.dst(end);
        v1 /= v0 * v0;
        if(v1 >= 0f && v1 <= 1f) {
            Intersector.tmp.set(end.x, end.y, 0f).sub(start.x, start.y, 0f);
            Intersector.tmp2.set(start.x, start.y, 0f).add(Intersector.tmp.scl(v1));
            v0 = Intersector.tmp2.dst(point.x, point.y, 0f);
            if(v0 < radius) {
                displacement.set(point).sub(Intersector.tmp2.x, Intersector.tmp2.y).nor();
                v2 = v0;
            }
        }

        return v2;
    }

    public static boolean intersectSegmentPlane(Vector3 start, Vector3 end, Plane plane, Vector3 intersection) {
        boolean v3;
        Vector3 v1 = end.tmp().sub(start);
        float v2 = -(start.dot(plane.getNormal()) + plane.getD()) / v1.dot(plane.getNormal());
        if(v2 < 0f || v2 > 1f) {
            v3 = false;
        }
        else {
            intersection.set(start).add(v1.scl(v2));
            v3 = true;
        }

        return v3;
    }

    public static boolean intersectSegmentPolygon(Vector2 p1, Vector2 p2, Polygon polygon) {
        boolean v21;
        float[] v8 = polygon.getTransformedVertices();
        float v12 = p1.x;
        float v17 = p1.y;
        float v13 = p2.x;
        float v18 = p2.y;
        float v9 = v12 - v13;
        float v4 = v17 - v18;
        float v1 = v12 * v18 - v17 * v13;
        int v7 = v8.length;
        float v14 = v8[v7 - 2];
        float v19 = v8[v7 - 1];
        int v6;
        for(v6 = 0; v6 < v7; v6 += 2) {
            float v15 = v8[v6];
            float v20 = v8[v6 + 1];
            float v2 = v14 * v20 - v19 * v15;
            float v10 = v14 - v15;
            float v5 = v19 - v20;
            float v3 = v9 * v5 - v4 * v10;
            float v11 = (v1 * v10 - v9 * v2) / v3;
            if(v11 >= v14 && v11 <= v15) {
                goto label_42;
            }
            else if(v11 >= v15 && v11 <= v14) {
            label_42:
                if(v11 < v12 || v11 > v13) {
                    if(v11 >= v13) {
                        if(v11 <= v12) {
                            goto label_46;
                        }

                        goto label_60;
                    }
                    else {
                        goto label_60;
                    }
                }

            label_46:
                float v16 = (v1 * v5 - v4 * v2) / v3;
                if(v16 < v19 || v16 > v20) {
                    if(v16 >= v20) {
                        if(v16 <= v19) {
                            goto label_54;
                        }

                        goto label_60;
                    }
                    else {
                        goto label_60;
                    }
                }

            label_54:
                if(v16 < v17 || v16 > v18) {
                    if(v16 >= v18) {
                        if(v16 <= v17) {
                            goto label_58;
                        }

                        goto label_60;
                    }
                    else {
                        goto label_60;
                    }
                }

            label_58:
                v21 = true;
                goto label_59;
            }

        label_60:
            v14 = v15;
            v19 = v20;
        }

        v21 = false;
    label_59:
        return v21;
    }

    public static boolean intersectSegments(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, Vector2 intersection) {
        boolean v6;
        float v1 = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        if(v1 == 0f) {
            v6 = false;
        }
        else {
            float v5 = y1 - y3;
            float v4 = x1 - x3;
            float v2 = ((x4 - x3) * v5 - (y4 - y3) * v4) / v1;
            if(v2 >= 0f && v2 <= 1f) {
                float v3 = ((x2 - x1) * v5 - (y2 - y1) * v4) / v1;
                if(v3 >= 0f && v3 <= 1f) {
                    if(intersection != null) {
                        intersection.set((x2 - x1) * v2 + x1, (y2 - y1) * v2 + y1);
                    }

                    v6 = true;
                    goto label_10;
                }

                v6 = false;
                goto label_10;
            }

            v6 = false;
        }

    label_10:
        return v6;
    }

    public static boolean intersectSegments(Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4, Vector2 intersection) {
        boolean v14;
        float v4 = p1.x;
        float v9 = p1.y;
        float v5 = p2.x;
        float v10 = p2.y;
        float v6 = p3.x;
        float v11 = p3.y;
        float v7 = p4.x;
        float v12 = p4.y;
        float v1 = (v12 - v11) * (v5 - v4) - (v7 - v6) * (v10 - v9);
        if(v1 == 0f) {
            v14 = false;
        }
        else {
            float v13 = v9 - v11;
            float v8 = v4 - v6;
            float v2 = ((v7 - v6) * v13 - (v12 - v11) * v8) / v1;
            if(v2 >= 0f && v2 <= 1f) {
                float v3 = ((v5 - v4) * v13 - (v10 - v9) * v8) / v1;
                if(v3 >= 0f && v3 <= 1f) {
                    if(intersection != null) {
                        intersection.set((v5 - v4) * v2 + v4, (v10 - v9) * v2 + v9);
                    }

                    v14 = true;
                    goto label_26;
                }

                v14 = false;
                goto label_26;
            }

            v14 = false;
        }

    label_26:
        return v14;
    }

    public static boolean isPointInPolygon(float[] polygon, int offset, int count, float x, float y) {
        boolean v3 = false;
        int v1 = offset + count - 2;
        int v0 = offset;
        int v2 = v1;
        while(v0 <= v2) {
            float v5 = polygon[v0 + 1];
            float v6 = polygon[v1 + 1];
            if(v5 < y && v6 >= y) {
                goto label_14;
            }
            else if(v6 < y && v5 >= y) {
            label_14:
                if((y - v5) / (v6 - v5) * (polygon[v1] - polygon[v0]) + polygon[v0] < x) {
                    if(!v3) {
                        v3 = true;
                    }
                    else {
                        v3 = false;
                    }
                }
            }

            v1 = v0;
            v0 += 2;
        }

        return v3;
    }

    public static boolean isPointInPolygon(Array arg8, Vector2 point) {
        Object v1 = arg8.peek();
        boolean v2 = false;
        int v0;
        for(v0 = 0; v0 < arg8.size; ++v0) {
            Object v3 = arg8.get(v0);
            if(((Vector2)v3).y < point.y && ((Vector2)v1).y >= point.y) {
                goto label_18;
            }
            else if(((Vector2)v1).y < point.y && ((Vector2)v3).y >= point.y) {
            label_18:
                if(((Vector2)v3).x + (point.y - ((Vector2)v3).y) / (((Vector2)v1).y - ((Vector2)v3).y) * (((Vector2)v1).x - ((Vector2)v3).x) < point.x) {
                    if(!v2) {
                        v2 = true;
                    }
                    else {
                        v2 = false;
                    }
                }
            }

            v1 = v3;
        }

        return v2;
    }

    public static boolean isPointInTriangle(float px, float py, float ax, float ay, float bx, float by, float cx, float cy) {
        boolean v3_1;
        int v3;
        int v2;
        float v0 = px - ax;
        float v1 = py - ay;
        if((bx - ax) * v1 - (by - ay) * v0 > 0f) {
            v2 = 1;
        }
        else {
            v2 = 0;
        }

        if((cx - ax) * v1 - (cy - ay) * v0 > 0f) {
            v3 = 1;
        }
        else {
            v3 = 0;
        }

        if(v3 == v2) {
            v3_1 = false;
        }
        else {
            if((cx - bx) * (py - by) - (cy - by) * (px - bx) > 0f) {
                v3 = 1;
            }
            else {
                v3 = 0;
            }

            if(v3 == v2) {
                goto label_40;
            }

            v3_1 = false;
            goto label_20;
        label_40:
            v3_1 = true;
        }

    label_20:
        return v3_1;
    }

    public static boolean isPointInTriangle(Vector2 p, Vector2 a, Vector2 b, Vector2 c) {
        int v5;
        int v2;
        boolean v4 = false;
        float v0 = p.x - a.x;
        float v1 = p.y - a.y;
        if((b.x - a.x) * v1 - (b.y - a.y) * v0 > 0f) {
            v2 = 1;
        }
        else {
            v2 = 0;
        }

        if((c.x - a.x) * v1 - (c.y - a.y) * v0 > 0f) {
            v5 = 1;
        }
        else {
            v5 = 0;
        }

        if(v5 != v2) {
            if((c.x - b.x) * (p.y - b.y) - (c.y - b.y) * (p.x - b.x) > 0f) {
                v5 = 1;
            }
            else {
                v5 = 0;
            }

            if(v5 != v2) {
                goto label_32;
            }

            v4 = true;
        }

    label_32:
        return v4;
    }

    public static boolean isPointInTriangle(Vector3 point, Vector3 t1, Vector3 t2, Vector3 t3) {
        boolean v5 = false;
        Intersector.v0.set(t1).sub(point);
        Intersector.v1.set(t2).sub(point);
        Intersector.v2.set(t3).sub(point);
        float v0 = Intersector.v0.dot(Intersector.v1);
        float v1 = Intersector.v0.dot(Intersector.v2);
        float v3 = Intersector.v1.dot(Intersector.v2);
        if(v3 * v1 - Intersector.v2.dot(Intersector.v2) * v0 >= 0f && v0 * v3 - v1 * Intersector.v1.dot(Intersector.v1) >= 0f) {
            v5 = true;
        }

        return v5;
    }

    public static void main(String[] args) {
        Plane v7 = new Plane(new Vector3(1f, 0f, 0f), 0f);
        SplitTriangle v10 = new SplitTriangle(3);
        Intersector.splitTriangle(new float[]{-10f, 0f, 10f, -1f, 0f, 0f, -10f, 0f, 10f}, v7, v10);
        System.out.println(v10);
        Intersector.splitTriangle(new float[]{-10f, 0f, 10f, 10f, 0f, 0f, -10f, 0f, -10f}, v7, v10);
        System.out.println(v10);
        Circle v2 = new Circle(0f, 0f, 1f);
        Circle v3 = new Circle(0f, 0f, 1f);
        Circle v4 = new Circle(2f, 0f, 1f);
        Circle v5 = new Circle(0f, 0f, 2f);
        System.out.println("Circle test cases");
        System.out.println(v2.overlaps(v2));
        System.out.println(v2.overlaps(v3));
        System.out.println(v2.overlaps(v4));
        System.out.println(v2.overlaps(v5));
        System.out.println(v5.overlaps(v2));
        System.out.println(v2.contains(0f, 1f));
        System.out.println(v2.contains(0f, 2f));
        System.out.println(v2.contains(v2));
        System.out.println(v2.contains(v5));
        System.out.println(v5.contains(v2));
        System.out.println("Rectangle test cases");
        Rectangle v8 = new Rectangle(0f, 0f, 1f, 1f);
        Rectangle v9 = new Rectangle(1f, 0f, 2f, 1f);
        System.out.println(v8.overlaps(v8));
        System.out.println(v8.overlaps(v9));
        System.out.println(v8.contains(0f, 0f));
        System.out.println("BoundingBox test cases");
        BoundingBox v0 = new BoundingBox(Vector3.Zero, new Vector3(1f, 1f, 1f));
        BoundingBox v1 = new BoundingBox(new Vector3(1f, 1f, 1f), new Vector3(2f, 2f, 2f));
        System.out.println(v0.contains(Vector3.Zero));
        System.out.println(v0.contains(v0));
        System.out.println(v0.contains(v1));
    }

    public static Vector2 nearestSegmentPoint(float startX, float startY, float endX, float endY, float pointX, float pointY, Vector2 nearest) {
        Vector2 v4;
        float v2 = endX - startX;
        float v3 = endY - startY;
        float v0 = v2 * v2 + v3 * v3;
        if(v0 == 0f) {
            v4 = nearest.set(startX, startY);
        }
        else {
            float v1 = ((pointX - startX) * (endX - startX) + (pointY - startY) * (endY - startY)) / v0;
            if(v1 < 0f) {
                v4 = nearest.set(startX, startY);
            }
            else if(v1 > 1f) {
                v4 = nearest.set(endX, endY);
            }
            else {
                v4 = nearest.set((endX - startX) * v1 + startX, (endY - startY) * v1 + startY);
            }
        }

        return v4;
    }

    public static Vector2 nearestSegmentPoint(Vector2 start, Vector2 end, Vector2 point, Vector2 nearest) {
        Vector2 v2;
        float v0 = start.dst2(end);
        if(v0 == 0f) {
            v2 = nearest.set(start);
        }
        else {
            float v1 = ((point.x - start.x) * (end.x - start.x) + (point.y - start.y) * (end.y - start.y)) / v0;
            if(v1 < 0f) {
                v2 = nearest.set(start);
            }
            else if(v1 > 1f) {
                v2 = nearest.set(end);
            }
            else {
                v2 = nearest.set(start.x + (end.x - start.x) * v1, start.y + (end.y - start.y) * v1);
            }
        }

        return v2;
    }

    public static boolean overlapConvexPolygons(Polygon p1, Polygon p2) {
        return Intersector.overlapConvexPolygons(p1, p2, null);
    }

    public static boolean overlapConvexPolygons(Polygon p1, Polygon p2, MinimumTranslationVector mtv) {
        return Intersector.overlapConvexPolygons(p1.getTransformedVertices(), p2.getTransformedVertices(), mtv);
    }

    public static boolean overlapConvexPolygons(float[] verts1, float[] verts2, MinimumTranslationVector mtv) {
        return Intersector.overlapConvexPolygons(verts1, 0, verts1.length, verts2, 0, verts2.length, mtv);
    }

    public static boolean overlapConvexPolygons(float[] verts1, int offset1, int count1, float[] verts2, int offset2, int count2, MinimumTranslationVector mtv) {
        float v12;
        float v15;
        float v16;
        float v11;
        float v14;
        float v18;
        float v10;
        float v13;
        float v9;
        float v4;
        float v3;
        float v22;
        float v23;
        float v21;
        float v17 = 340282346638528860000000000000000000000f;
        float v19 = 0f;
        float v20 = 0f;
        int v5 = offset1 + count1;
        int v6 = offset2 + count2;
        int v7 = offset1;
        while(true) {
            if(v7 < v5) {
                v21 = verts1[v7];
                v23 = verts1[v7 + 1];
                v22 = verts1[(v7 + 2) % count1];
                v3 = v23 - verts1[(v7 + 3) % count1];
                v4 = -(v21 - v22);
                v9 = ((float)Math.sqrt(((double)(v3 * v3 + v4 * v4))));
                v3 /= v9;
                v4 /= v9;
                v13 = verts1[0] * v3 + verts1[1] * v4;
                v10 = v13;
                int v8;
                for(v8 = offset1; v8 < v5; v8 += 2) {
                    v18 = verts1[v8] * v3 + verts1[v8 + 1] * v4;
                    if(v18 < v13) {
                        v13 = v18;
                    }
                    else if(v18 > v10) {
                        v10 = v18;
                    }
                }

                v14 = verts2[0] * v3 + verts2[1] * v4;
                v11 = v14;
                for(v8 = offset2; v8 < v6; v8 += 2) {
                    v18 = verts2[v8] * v3 + verts2[v8 + 1] * v4;
                    if(v18 < v14) {
                        v14 = v18;
                    }
                    else if(v18 > v11) {
                        v11 = v18;
                    }
                }

                if((v13 > v14 || v10 < v14) && (v14 > v13 || v11 < v13)) {
                    break;
                }

                v16 = Math.min(v10, v11) - Math.max(v13, v14);
                if(v13 < v14 && v10 > v11) {
                    goto label_90;
                }
                else if(v14 < v13 && v11 > v10) {
                label_90:
                    v15 = Math.abs(v13 - v14);
                    v12 = Math.abs(v10 - v11);
                    if(v15 < v12) {
                        v3 = -v3;
                        v4 = -v4;
                        v16 += v15;
                    }
                    else {
                        v16 += v12;
                    }
                }

                if(v16 < v17) {
                    v17 = v16;
                    v19 = v3;
                    v20 = v4;
                }

                v7 += 2;
                continue;
            }
            else {
                goto label_106;
            }
        }

        boolean v25 = false;
        goto label_82;
    label_106:
        for(v7 = offset2; true; v7 += 2) {
            if(v7 >= v6) {
                goto label_207;
            }

            v21 = verts2[v7];
            v23 = verts2[v7 + 1];
            v22 = verts2[(v7 + 2) % count2];
            v3 = v23 - verts2[(v7 + 3) % count2];
            v4 = -(v21 - v22);
            v9 = ((float)Math.sqrt(((double)(v3 * v3 + v4 * v4))));
            v3 /= v9;
            v4 /= v9;
            v13 = verts1[0] * v3 + verts1[1] * v4;
            v10 = v13;
            v8 = offset1;
            while(v8 < v5) {
                v18 = verts1[v8] * v3 + verts1[v8 + 1] * v4;
                if(v18 < v13) {
                    v13 = v18;
                }
                else if(v18 > v10) {
                    v10 = v18;
                }

                v8 += 2;
            }

            v14 = verts2[0] * v3 + verts2[1] * v4;
            v11 = v14;
            v8 = offset2;
            while(v8 < v6) {
                v18 = verts2[v8] * v3 + verts2[v8 + 1] * v4;
                if(v18 < v14) {
                    v14 = v18;
                }
                else if(v18 > v11) {
                    v11 = v18;
                }

                v8 += 2;
            }

            if((v13 > v14 || v10 < v14) && (v14 > v13 || v11 < v13)) {
                break;
            }

            v16 = Math.min(v10, v11) - Math.max(v13, v14);
            if(v13 < v14 && v10 > v11) {
                goto label_191;
            }
            else if(v14 < v13 && v11 > v10) {
            label_191:
                v15 = Math.abs(v13 - v14);
                v12 = Math.abs(v10 - v11);
                if(v15 < v12) {
                    v3 = -v3;
                    v4 = -v4;
                    v16 += v15;
                }
                else {
                    v16 += v12;
                }
            }

            if(v16 < v17) {
                v17 = v16;
                v19 = v3;
                v20 = v4;
            }
        }

        v25 = false;
        goto label_82;
    label_207:
        if(mtv != null) {
            mtv.normal.set(v19, v20);
            mtv.depth = v17;
        }

        v25 = true;
    label_82:
        return v25;
    }

    public static boolean overlaps(Circle c1, Circle c2) {
        return c1.overlaps(c2);
    }

    public static boolean overlaps(Circle c, Rectangle r) {
        boolean v2;
        float v0 = c.x;
        float v1 = c.y;
        if(c.x < r.x) {
            v0 = r.x;
        }
        else if(c.x > r.x + r.width) {
            v0 = r.x + r.width;
        }

        if(c.y < r.y) {
            v1 = r.y;
        }
        else if(c.y > r.y + r.height) {
            v1 = r.y + r.height;
        }

        v0 -= c.x;
        v1 -= c.y;
        if(v0 * v0 + v1 * v1 < c.radius * c.radius) {
            v2 = true;
        }
        else {
            v2 = false;
        }

        return v2;
    }

    public static boolean overlaps(Rectangle r1, Rectangle r2) {
        return r1.overlaps(r2);
    }

    public static int pointLineSide(float linePoint1X, float linePoint1Y, float linePoint2X, float linePoint2Y, float pointX, float pointY) {
        return ((int)Math.signum((linePoint2X - linePoint1X) * (pointY - linePoint1Y) - (linePoint2Y - linePoint1Y) * (pointX - linePoint1X)));
    }

    public static int pointLineSide(Vector2 linePoint1, Vector2 linePoint2, Vector2 point) {
        return ((int)Math.signum((linePoint2.x - linePoint1.x) * (point.y - linePoint1.y) - (linePoint2.y - linePoint1.y) * (point.x - linePoint1.x)));
    }

    private static void splitEdge(float[] vertices, int s, int e, int stride, Plane plane, float[] split, int offset) {
        float v11 = Intersector.intersectLinePlane(vertices[s], vertices[s + 1], vertices[s + 2], vertices[e], vertices[e + 1], vertices[e + 2], plane, Intersector.intersection);
        split[offset] = Intersector.intersection.x;
        split[offset + 1] = Intersector.intersection.y;
        split[offset + 2] = Intersector.intersection.z;
        int v10;
        for(v10 = 3; v10 < stride; ++v10) {
            split[offset + v10] = (vertices[e + v10] - vertices[s + v10]) * v11 + vertices[s + v10];
        }
    }

    public static void splitTriangle(float[] triangle, Plane plane, SplitTriangle split) {
        boolean v0_1;
        int v0;
        int v4;
        boolean v9;
        boolean v8;
        boolean v7;
        int v11 = 2;
        boolean v10 = true;
        int v3 = triangle.length / 3;
        if(plane.testPoint(triangle[0], triangle[1], triangle[v11]) == PlaneSide.Back) {
            v7 = true;
        }
        else {
            v7 = false;
        }

        if(plane.testPoint(triangle[v3], triangle[v3 + 1], triangle[v3 + 2]) == PlaneSide.Back) {
            v8 = true;
        }
        else {
            v8 = false;
        }

        if(plane.testPoint(triangle[v3 * 2], triangle[v3 * 2 + 1], triangle[v3 * 2 + 2]) == PlaneSide.Back) {
            v9 = true;
        }
        else {
            v9 = false;
        }

        split.reset();
        if(v7 != v8 || v8 != v9) {
            split.total = 3;
            if(v7) {
                v4 = 1;
            }
            else {
                v4 = 0;
            }

            if(v8) {
                v0 = 1;
            }
            else {
                v0 = 0;
            }

            v4 += v0;
            if(v9) {
                v0 = 1;
            }
            else {
                v0 = 0;
            }

            split.numFront = v0 + v4;
            split.numBack = split.total - split.numFront;
            split.setSide(v7);
            int v2 = v3;
            if(v7 != v8) {
                Intersector.splitEdge(triangle, 0, v2, v3, plane, split.edgeSplit, 0);
                split.add(triangle, 0, v3);
                split.add(split.edgeSplit, 0, v3);
                if(!split.getSide()) {
                    v0_1 = true;
                }
                else {
                    v0_1 = false;
                }

                split.setSide(v0_1);
                split.add(split.edgeSplit, 0, v3);
            }
            else {
                split.add(triangle, 0, v3);
            }

            int v1 = v3;
            v2 = v3 + v3;
            if(v8 != v9) {
                Intersector.splitEdge(triangle, v1, v2, v3, plane, split.edgeSplit, 0);
                split.add(triangle, v1, v3);
                split.add(split.edgeSplit, 0, v3);
                if(!split.getSide()) {
                    v0_1 = true;
                }
                else {
                    v0_1 = false;
                }

                split.setSide(v0_1);
                split.add(split.edgeSplit, 0, v3);
            }
            else {
                split.add(triangle, v1, v3);
            }

            v1 = v3 + v3;
            if(v9 != v7) {
                Intersector.splitEdge(triangle, v1, 0, v3, plane, split.edgeSplit, 0);
                split.add(triangle, v1, v3);
                split.add(split.edgeSplit, 0, v3);
                if(split.getSide()) {
                    v10 = false;
                }

                split.setSide(v10);
                split.add(split.edgeSplit, 0, v3);
            }
            else {
                split.add(triangle, v1, v3);
            }

            if(split.numFront != v11) {
                goto label_150;
            }

            System.arraycopy(split.front, v3 * 2, split.front, v3 * 3, v3 * 2);
            System.arraycopy(split.front, 0, split.front, v3 * 5, v3);
            return;
        label_150:
            System.arraycopy(split.back, v3 * 2, split.back, v3 * 3, v3 * 2);
            System.arraycopy(split.back, 0, split.back, v3 * 5, v3);
        }
        else {
            split.total = 1;
            if(v7) {
                split.numBack = 1;
                System.arraycopy(triangle, 0, split.back, 0, triangle.length);
            }
            else {
                split.numFront = 1;
                System.arraycopy(triangle, 0, split.front, 0, triangle.length);
            }
        }
    }
}

