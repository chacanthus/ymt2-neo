// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.Iterator;

public class ModelBuilder {
    private Array builders;
    private Model model;
    private Node node;

    public ModelBuilder() {
        super();
        this.builders = new Array();
    }

    public void begin() {
        if(this.model != null) {
            throw new GdxRuntimeException("Call end() first");
        }

        this.node = null;
        this.model = new Model();
        this.builders.clear();
    }

    public Model createBox(float width, float height, float depth, int primitiveType, Material material, long attributes) {
        this.begin();
        this.part("box", primitiveType, attributes, material).box(width, height, depth);
        return this.end();
    }

    public Model createBox(float width, float height, float depth, Material material, long attributes) {
        return this.createBox(width, height, depth, 4, material, attributes);
    }

    public Model createCapsule(float radius, float height, int divisions, int primitiveType, Material material, long attributes) {
        this.begin();
        this.part("capsule", primitiveType, attributes, material).capsule(radius, height, divisions);
        return this.end();
    }

    public Model createCapsule(float radius, float height, int divisions, Material material, long attributes) {
        return this.createCapsule(radius, height, divisions, 4, material, attributes);
    }

    public Model createCone(float width, float height, float depth, int divisions, int primitiveType, Material material, long attributes) {
        return this.createCone(width, height, depth, divisions, primitiveType, material, attributes, 0f, 360f);
    }

    public Model createCone(float width, float height, float depth, int divisions, int primitiveType, Material material, long attributes, float angleFrom, float angleTo) {
        this.begin();
        this.part("cone", primitiveType, attributes, material).cone(width, height, depth, divisions, angleFrom, angleTo);
        return this.end();
    }

    public Model createCone(float width, float height, float depth, int divisions, Material material, long attributes) {
        return this.createCone(width, height, depth, divisions, 4, material, attributes);
    }

    public Model createCone(float width, float height, float depth, int divisions, Material material, long attributes, float angleFrom, float angleTo) {
        return this.createCone(width, height, depth, divisions, 4, material, attributes, angleFrom, angleTo);
    }

    public Model createCylinder(float width, float height, float depth, int divisions, int primitiveType, Material material, long attributes) {
        return this.createCylinder(width, height, depth, divisions, primitiveType, material, attributes, 0f, 360f);
    }

    public Model createCylinder(float width, float height, float depth, int divisions, int primitiveType, Material material, long attributes, float angleFrom, float angleTo) {
        this.begin();
        this.part("cylinder", primitiveType, attributes, material).cylinder(width, height, depth, divisions, angleFrom, angleTo);
        return this.end();
    }

    public Model createCylinder(float width, float height, float depth, int divisions, Material material, long attributes) {
        return this.createCylinder(width, height, depth, divisions, 4, material, attributes);
    }

    public Model createCylinder(float width, float height, float depth, int divisions, Material material, long attributes, float angleFrom, float angleTo) {
        return this.createCylinder(width, height, depth, divisions, 4, material, attributes, angleFrom, angleTo);
    }

    public static Model createFromMesh(Mesh mesh, int indexOffset, int vertexCount, int primitiveType, Material material) {
        Model v3 = new Model();
        MeshPart v0 = new MeshPart();
        v0.id = "part1";
        v0.indexOffset = indexOffset;
        v0.numVertices = vertexCount;
        v0.primitiveType = primitiveType;
        v0.mesh = mesh;
        NodePart v2 = new NodePart();
        v2.material = material;
        v2.meshPart = v0;
        Node v1 = new Node();
        v1.id = "node1";
        v1.parts.add(v2);
        v3.meshes.add(mesh);
        v3.materials.add(material);
        v3.nodes.add(v1);
        v3.meshParts.add(v0);
        v3.manageDisposable(((Disposable)mesh));
        return v3;
    }

    public static Model createFromMesh(Mesh mesh, int primitiveType, Material material) {
        return ModelBuilder.createFromMesh(mesh, 0, mesh.getNumIndices(), primitiveType, material);
    }

    public static Model createFromMesh(float[] vertices, VertexAttribute[] attributes, short[] indices, int primitiveType, Material material) {
        Mesh v0 = new Mesh(false, vertices.length, indices.length, attributes);
        v0.setVertices(vertices);
        v0.setIndices(indices);
        return ModelBuilder.createFromMesh(v0, 0, indices.length, primitiveType, material);
    }

    public Model createRect(float x00, float y00, float z00, float x10, float y10, float z10, float x11, float y11, float z11, float x01, float y01, float z01, float normalX, float normalY, float normalZ, int primitiveType, Material material, long attributes) {
        this.begin();
        this.part("rect", primitiveType, attributes, material).rect(x00, y00, z00, x10, y10, z10, x11, y11, z11, x01, y01, z01, normalX, normalY, normalZ);
        return this.end();
    }

    public Model createRect(float x00, float y00, float z00, float x10, float y10, float z10, float x11, float y11, float z11, float x01, float y01, float z01, float normalX, float normalY, float normalZ, Material material, long attributes) {
        return this.createRect(x00, y00, z00, x10, y10, z10, x11, y11, z11, x01, y01, z01, normalX, normalY, normalZ, 4, material, attributes);
    }

    public Model createSphere(float width, float height, float depth, int divisionsU, int divisionsV, int primitiveType, Material material, long attributes) {
        return this.createSphere(width, height, depth, divisionsU, divisionsV, primitiveType, material, attributes, 0f, 360f, 0f, 180f);
    }

    public Model createSphere(float width, float height, float depth, int divisionsU, int divisionsV, int primitiveType, Material material, long attributes, float angleUFrom, float angleUTo, float angleVFrom, float angleVTo) {
        this.begin();
        this.part("cylinder", primitiveType, attributes, material).sphere(width, height, depth, divisionsU, divisionsV, angleUFrom, angleUTo, angleVFrom, angleVTo);
        return this.end();
    }

    public Model createSphere(float width, float height, float depth, int divisionsU, int divisionsV, Material material, long attributes) {
        return this.createSphere(width, height, depth, divisionsU, divisionsV, 4, material, attributes);
    }

    public Model createSphere(float width, float height, float depth, int divisionsU, int divisionsV, Material material, long attributes, float angleUFrom, float angleUTo, float angleVFrom, float angleVTo) {
        return this.createSphere(width, height, depth, divisionsU, divisionsV, 4, material, attributes, angleUFrom, angleUTo, angleVFrom, angleVTo);
    }

    public Model end() {
        if(this.model == null) {
            throw new GdxRuntimeException("Call begin() first");
        }

        Model v2 = this.model;
        this.endnode();
        this.model = null;
        Iterator v0 = this.builders.iterator();
        while(v0.hasNext()) {
            v0.next().end();
        }

        this.builders.clear();
        ModelBuilder.rebuildReferences(v2);
        return v2;
    }

    private void endnode() {
        if(this.node != null) {
            this.node = null;
        }
    }

    private MeshBuilder getBuilder(VertexAttributes attributes) {
        Object v1;
        Iterator v0 = this.builders.iterator();
        do {
            if(v0.hasNext()) {
                v1 = v0.next();
                if(!((MeshBuilder)v1).getAttributes().equals(attributes)) {
                    continue;
                }

                if(((MeshBuilder)v1).lastIndex() >= 16383) {
                    continue;
                }
            }
            else {
                break;
            }

            goto label_11;
        }
        while(true);

        MeshBuilder v2 = new MeshBuilder();
        v2.begin(attributes);
        this.builders.add(v2);
        MeshBuilder v1_1 = v2;
    label_11:
        return ((MeshBuilder)v1);
    }

    public void manage(Disposable disposable) {
        if(this.model == null) {
            throw new GdxRuntimeException("Call begin() first");
        }

        this.model.manageDisposable(disposable);
    }

    public Node node() {
        Node v0 = new Node();
        this.node(v0);
        v0.id = "node" + this.model.nodes.size;
        return v0;
    }

    protected Node node(Node node) {
        if(this.model == null) {
            throw new GdxRuntimeException("Call begin() first");
        }

        this.endnode();
        this.model.nodes.add(node);
        this.node = node;
        return node;
    }

    public Node node(String id, Model model) {
        Node v2 = new Node();
        v2.id = id;
        v2.children.addAll(model.nodes);
        this.node(v2);
        Iterator v1 = model.getManagedDisposables().iterator();
        while(v1.hasNext()) {
            this.manage(v1.next());
        }

        return v2;
    }

    private MeshPartBuilder part(String id, int primitiveType, VertexAttributes attributes, Material material) {
        MeshBuilder v0 = this.getBuilder(attributes);
        this.part(v0.part(id, primitiveType), material);
        return ((MeshPartBuilder)v0);
    }

    public void part(MeshPart meshpart, Material material) {
        if(this.node == null) {
            this.node();
        }

        this.node.parts.add(new NodePart(meshpart, material));
    }

    public MeshPartBuilder part(String id, int primitiveType, long attributes, Material material) {
        return this.part(id, primitiveType, MeshBuilder.createAttributes(attributes), material);
    }

    public MeshPart part(String id, Mesh mesh, int primitiveType, int offset, int size, Material material) {
        MeshPart v0 = new MeshPart();
        v0.id = id;
        v0.primitiveType = primitiveType;
        v0.mesh = mesh;
        v0.indexOffset = offset;
        v0.numVertices = size;
        this.part(v0, material);
        return v0;
    }

    public MeshPart part(String id, Mesh mesh, int primitiveType, Material material) {
        return this.part(id, mesh, primitiveType, 0, mesh.getNumIndices(), material);
    }

    public static void rebuildReferences(Model model) {
        model.materials.clear();
        model.meshes.clear();
        model.meshParts.clear();
        Iterator v0 = model.nodes.iterator();
        while(v0.hasNext()) {
            ModelBuilder.rebuildReferences(model, v0.next());
        }
    }

    private static void rebuildReferences(Model model, Node node) {
        Iterator v1 = node.parts.iterator();
        while(true) {
            if(!v1.hasNext()) {
                break;
            }

            Object v2 = v1.next();
            if(!model.materials.contains(((NodePart)v2).material, true)) {
                model.materials.add(((NodePart)v2).material);
            }

            if(model.meshParts.contains(((NodePart)v2).meshPart, true)) {
                continue;
            }

            model.meshParts.add(((NodePart)v2).meshPart);
            if(!model.meshes.contains(((NodePart)v2).meshPart.mesh, true)) {
                model.meshes.add(((NodePart)v2).meshPart.mesh);
            }

            model.manageDisposable(((NodePart)v2).meshPart.mesh);
        }

        v1 = node.children.iterator();
        while(v1.hasNext()) {
            ModelBuilder.rebuildReferences(model, v1.next());
        }
    }
}

