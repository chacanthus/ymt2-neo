// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader$TextureParameter;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture$TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.ImageResolver;
import com.badlogic.gdx.maps.ImageResolver$AssetManagerImageResolver;
import com.badlogic.gdx.maps.ImageResolver$DirectImageResolver;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader$Element;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class TmxMapLoader extends AsynchronousAssetLoader {
    public class Parameters extends AssetLoaderParameters {
        public boolean convertObjectToTileSpace;
        public boolean yUp;

        public Parameters() {
            super();
            this.yUp = true;
            this.generateMipMaps = false;
            this.textureMinFilter = TextureFilter.Nearest;
            this.textureMagFilter = TextureFilter.Nearest;
            this.convertObjectToTileSpace = false;
        }
    }

    protected static final int FLAG_FLIP_DIAGONALLY = 536870912;
    protected static final int FLAG_FLIP_HORIZONTALLY = -2147483648;
    protected static final int FLAG_FLIP_VERTICALLY = 1073741824;
    protected static final int MASK_CLEAR = -536870912;
    protected boolean convertObjectToTileSpace;
    protected TiledMap map;
    protected int mapHeightInPixels;
    protected int mapTileHeight;
    protected int mapTileWidth;
    protected int mapWidthInPixels;
    protected Element root;
    protected XmlReader xml;
    protected boolean yUp;

    public TmxMapLoader() {
        super(new InternalFileHandleResolver());
        this.xml = new XmlReader();
    }

    public TmxMapLoader(FileHandleResolver resolver) {
        super(resolver);
        this.xml = new XmlReader();
    }

    protected Cell createTileLayerCell(boolean flipHorizontally, boolean flipVertically, boolean flipDiagonally) {
        int v1 = 3;
        int v2 = 1;
        Cell v0 = new Cell();
        if(flipDiagonally) {
            if((flipHorizontally) && (flipVertically)) {
                v0.setFlipHorizontally(true);
                if(!this.yUp) {
                    v1 = 1;
                }

                v0.setRotation(v1);
                goto label_10;
            }

            if(!flipHorizontally) {
                goto label_20;
            }

            if(!this.yUp) {
                v1 = 1;
            }

            v0.setRotation(v1);
            goto label_10;
        label_20:
            if(!flipVertically) {
                goto label_27;
            }

            if(!this.yUp) {
                v2 = v1;
            }

            v0.setRotation(v2);
            goto label_10;
        label_27:
            v0.setFlipVertically(true);
            if(!this.yUp) {
                v1 = 1;
            }

            v0.setRotation(v1);
        }
        else {
            v0.setFlipHorizontally(flipHorizontally);
            v0.setFlipVertically(flipVertically);
        }

    label_10:
        return v0;
    }

    public Array getDependencies(String x0, FileHandle x1, AssetLoaderParameters x2) {
        return this.getDependencies(x0, x1, ((Parameters)x2));
    }

    public Array getDependencies(String fileName, FileHandle tmxFile, Parameters parameter) {  // has try-catch handlers
        boolean v2;
        Array v0 = new Array();
        try {
            this.root = this.xml.parse(tmxFile);
            if(parameter != null) {
                v2 = parameter.generateMipMaps;
            }
            else {
                goto label_33;
            }

            goto label_6;
        }
        catch(IOException v1) {
            goto label_32;
        }

    label_33:
        v2 = false;
        try {
        label_6:
            TextureParameter v5 = new TextureParameter();
            v5.genMipMaps = v2;
            if(parameter != null) {
                v5.minFilter = parameter.textureMinFilter;
                v5.magFilter = parameter.textureMagFilter;
            }

            Iterator v3 = this.loadTilesets(this.root, tmxFile).iterator();
            while(v3.hasNext()) {
                v0.add(new AssetDescriptor(v3.next(), Texture.class, ((AssetLoaderParameters)v5)));
            }
        }
        catch(IOException v1) {
            goto label_32;
        }

        return v0;
    label_32:
        throw new GdxRuntimeException("Couldn\'t load tilemap \'" + fileName + "\'", ((Throwable)v1));
    }

    protected static FileHandle getRelativeFileHandle(FileHandle file, String path) {
        StringTokenizer v2 = new StringTokenizer(path, "\\/");
        FileHandle v0;
        for(v0 = file.parent(); true; v0 = v0.child(v1)) {
            if(!v2.hasMoreElements()) {
                break;
            }

            String v1 = v2.nextToken();
            if(!v1.equals("..")) {
                goto label_11;
            }

            v0 = v0.parent();
            continue;
        label_11:
        }

        return v0;
    }

    public TiledMap load(String fileName) {
        return this.load(fileName, new Parameters());
    }

    public TiledMap load(String fileName, Parameters parameters) {  // has try-catch handlers
        try {
            this.yUp = parameters.yUp;
            this.convertObjectToTileSpace = parameters.convertObjectToTileSpace;
            FileHandle v7 = this.resolve(fileName);
            this.root = this.xml.parse(v7);
            ObjectMap v6 = new ObjectMap();
            Iterator v1 = this.loadTilesets(this.root, v7).iterator();
            while(v1.hasNext()) {
                Object v5 = v1.next();
                Texture v4 = new Texture(((FileHandle)v5), parameters.generateMipMaps);
                v4.setFilter(parameters.textureMinFilter, parameters.textureMagFilter);
                v6.put(((FileHandle)v5).path(), v4);
            }

            TiledMap v3 = this.loadTilemap(this.root, v7, new DirectImageResolver(v6));
            v3.setOwnedResources(v6.values().toArray());
            return v3;
        }
        catch(IOException v0) {
            throw new GdxRuntimeException("Couldn\'t load tilemap \'" + fileName + "\'", ((Throwable)v0));
        }
    }

    public void loadAsync(AssetManager x0, String x1, FileHandle x2, AssetLoaderParameters x3) {
        this.loadAsync(x0, x1, x2, ((Parameters)x3));
    }

    public void loadAsync(AssetManager manager, String fileName, FileHandle tmxFile, Parameters parameter) {  // has try-catch handlers
        this.map = null;
        if(parameter != null) {
            this.yUp = parameter.yUp;
            this.convertObjectToTileSpace = parameter.convertObjectToTileSpace;
        }
        else {
            this.yUp = true;
            this.convertObjectToTileSpace = false;
        }

        try {
            this.map = this.loadTilemap(this.root, tmxFile, new AssetManagerImageResolver(manager));
            return;
        }
        catch(Exception v0) {
            throw new GdxRuntimeException("Couldn\'t load tilemap \'" + fileName + "\'", ((Throwable)v0));
        }
    }

    protected void loadObject(MapLayer layer, Element element) {
        boolean v20_3;
        float v20_1;
        PolylineMapObject v7_1;
        String[] v8;
        float[] v16;
        String[] v9;
        int v20;
        float v14;
        float v13;
        if(element.getName().equals("object")) {
            PolygonMapObject v7 = null;
            if(this.convertObjectToTileSpace) {
                v13 = 1f / (((float)this.mapTileWidth));
            }
            else {
                v13 = 1f;
            }

            if(this.convertObjectToTileSpace) {
                v14 = 1f / (((float)this.mapTileHeight));
            }
            else {
                v14 = 1f;
            }

            float v18 = (((float)element.getIntAttribute("x", 0))) * v13;
            if(this.yUp) {
                v20 = this.mapHeightInPixels - element.getIntAttribute("y", 0);
            }
            else {
                v20 = element.getIntAttribute("y", 0);
            }

            float v19 = (((float)v20)) * v14;
            float v17 = (((float)element.getIntAttribute("width", 0))) * v13;
            float v5 = (((float)element.getIntAttribute("height", 0))) * v14;
            if(element.getChildCount() > 0) {
                Element v3 = element.getChildByName("polygon");
                if(v3 != null) {
                    v9 = v3.getAttribute("points").split(" ");
                    v16 = new float[v9.length * 2];
                    int v6;
                    for(v6 = 0; v6 < v9.length; ++v6) {
                        v8 = v9[v6].split(",");
                        v16[v6 * 2] = (((float)Integer.parseInt(v8[0]))) * v13;
                        v16[v6 * 2 + 1] = (((float)Integer.parseInt(v8[1]))) * v14;
                        if(this.yUp) {
                            v20 = v6 * 2 + 1;
                            v16[v20] *= -1f;
                        }
                    }

                    Polygon v10 = new Polygon(v16);
                    v10.setPosition(v18, v19);
                    v7 = new PolygonMapObject(v10);
                }
                else {
                    v3 = element.getChildByName("polyline");
                    if(v3 == null) {
                        goto label_298;
                    }

                    v9 = v3.getAttribute("points").split(" ");
                    v16 = new float[v9.length * 2];
                    for(v6 = 0; v6 < v9.length; ++v6) {
                        v8 = v9[v6].split(",");
                        v16[v6 * 2] = (((float)Integer.parseInt(v8[0]))) * v13;
                        v16[v6 * 2 + 1] = (((float)Integer.parseInt(v8[1]))) * v14;
                        if(this.yUp) {
                            v20 = v6 * 2 + 1;
                            v16[v20] *= -1f;
                        }
                    }

                    Polyline v11 = new Polyline(v16);
                    v11.setPosition(v18, v19);
                    v7_1 = new PolylineMapObject(v11);
                    goto label_152;
                label_298:
                    if(element.getChildByName("ellipse") == null) {
                        goto label_152;
                    }

                    if(this.yUp) {
                        v20_1 = v19 - v5;
                    }
                    else {
                        v20_1 = v19;
                    }

                    EllipseMapObject v7_2 = new EllipseMapObject(v18, v20_1, v17, v5);
                }
            }

        label_152:
            if((((PolygonMapObject)v7_1)) == null) {
                if(this.yUp) {
                    v20_1 = v19 - v5;
                }
                else {
                    v20_1 = v19;
                }

                RectangleMapObject v7_3 = new RectangleMapObject(v18, v20_1, v17, v5);
            }

            ((MapObject)v7_1).setName(element.getAttribute("name", null));
            String v15 = element.getAttribute("type", null);
            if(v15 != null) {
                ((MapObject)v7_1).getProperties().put("type", v15);
            }

            int v4 = element.getIntAttribute("gid", -1);
            if(v4 != -1) {
                ((MapObject)v7_1).getProperties().put("gid", Integer.valueOf(v4));
            }

            ((MapObject)v7_1).getProperties().put("x", Float.valueOf(v18 * v13));
            MapProperties v20_2 = ((MapObject)v7_1).getProperties();
            String v21 = "y";
            if(this.yUp) {
                v19 -= v5;
            }

            v20_2.put(v21, Float.valueOf(v19 * v14));
            if(element.getIntAttribute("visible", 1) == 1) {
                v20_3 = true;
            }
            else {
                v20_3 = false;
            }

            ((MapObject)v7_1).setVisible(v20_3);
            Element v12 = element.getChildByName("properties");
            if(v12 != null) {
                this.loadProperties(((MapObject)v7_1).getProperties(), v12);
            }

            layer.getObjects().add(((MapObject)v7_1));
        }
    }

    protected void loadObjectGroup(TiledMap map, Element element) {
        if(element.getName().equals("objectgroup")) {
            String v2 = element.getAttribute("name", null);
            MapLayer v1 = new MapLayer();
            v1.setName(v2);
            Element v4 = element.getChildByName("properties");
            if(v4 != null) {
                this.loadProperties(v1.getProperties(), v4);
            }

            Iterator v0 = element.getChildrenByName("object").iterator();
            while(v0.hasNext()) {
                this.loadObject(v1, v0.next());
            }

            map.getLayers().add(v1);
        }
    }

    protected void loadProperties(MapProperties properties, Element element) {
        String v6 = null;
        if(element.getName().equals("properties")) {
            Iterator v0 = element.getChildrenByName("property").iterator();
            while(v0.hasNext()) {
                Object v2 = v0.next();
                String v1 = ((Element)v2).getAttribute("name", v6);
                String v3 = ((Element)v2).getAttribute("value", v6);
                if(v3 == null) {
                    v3 = ((Element)v2).getText();
                }

                properties.put(v1, v3);
            }
        }
    }

    public TiledMap loadSync(AssetManager manager, String fileName, FileHandle file, Parameters parameter) {
        return this.map;
    }

    public Object loadSync(AssetManager x0, String x1, FileHandle x2, AssetLoaderParameters x3) {
        return this.loadSync(x0, x1, x2, ((Parameters)x3));
    }

    protected void loadTileLayer(TiledMap map, Element element) {
        int v22;
        boolean v4;
        boolean v6;
        boolean v5;
        boolean v18;
        if(element.getName().equals("layer")) {
            String v11 = element.getAttribute("name", null);
            int v19 = element.getIntAttribute("width", 0);
            int v7 = element.getIntAttribute("height", 0);
            int v16 = element.getParent().getIntAttribute("tilewidth", 0);
            int v15 = element.getParent().getIntAttribute("tileheight", 0);
            if(element.getIntAttribute("visible", 1) == 1) {
                v18 = true;
            }
            else {
                v18 = false;
            }

            float v12 = element.getFloatAttribute("opacity", 1f);
            TiledMapTileLayer v10 = new TiledMapTileLayer(v19, v7, v16, v15);
            v10.setVisible(v18);
            v10.setOpacity(v12);
            v10.setName(v11);
            int[] v9 = TmxMapHelper.getTileIds(element, v19, v7);
            TiledMapTileSets v17 = map.getTileSets();
            int v21;
            for(v21 = 0; v21 < v7; ++v21) {
                int v20;
                for(v20 = 0; v20 < v19; ++v20) {
                    int v8 = v9[v21 * v19 + v20];
                    if((-2147483648 & v8) != 0) {
                        v5 = true;
                    }
                    else {
                        v5 = false;
                    }

                    if((1073741824 & v8) != 0) {
                        v6 = true;
                    }
                    else {
                        v6 = false;
                    }

                    if((536870912 & v8) != 0) {
                        v4 = true;
                    }
                    else {
                        v4 = false;
                    }

                    TiledMapTile v14 = v17.getTile(536870911 & v8);
                    if(v14 != null) {
                        Cell v3 = this.createTileLayerCell(v5, v6, v4);
                        v3.setTile(v14);
                        if(this.yUp) {
                            v22 = v7 - 1 - v21;
                        }
                        else {
                            v22 = v21;
                        }

                        v10.setCell(v20, v22, v3);
                    }
                }
            }

            Element v13 = element.getChildByName("properties");
            if(v13 != null) {
                this.loadProperties(v10.getProperties(), v13);
            }

            map.getLayers().add(((MapLayer)v10));
        }
    }

    protected void loadTileSet(TiledMap map, Element element, FileHandle tmxFile, ImageResolver imageResolver) {  // has try-catch handlers
        Element v22;
        FileHandle v14;
        int v15;
        int v17;
        String v16;
        if(element.getName().equals("tileset")) {
            String v20 = element.get("name", null);
            int v10 = element.getIntAttribute("firstgid", 1);
            int v7 = element.getIntAttribute("tilewidth", 0);
            int v8 = element.getIntAttribute("tileheight", 0);
            int v25 = element.getIntAttribute("spacing", 0);
            int v19 = element.getIntAttribute("margin", 0);
            String v24 = element.getAttribute("source", null);
            if(v24 != null) {
                FileHandle v33 = TmxMapLoader.getRelativeFileHandle(tmxFile, v24);
                try {
                    element = this.xml.parse(v33);
                    v20 = element.get("name", null);
                    v7 = element.getIntAttribute("tilewidth", 0);
                    v8 = element.getIntAttribute("tileheight", 0);
                    v25 = element.getIntAttribute("spacing", 0);
                    v19 = element.getIntAttribute("margin", 0);
                    v16 = element.getChildByName("image").getAttribute("source");
                    v17 = element.getChildByName("image").getIntAttribute("width", 0);
                    v15 = element.getChildByName("image").getIntAttribute("height", 0);
                    v14 = TmxMapLoader.getRelativeFileHandle(v33, v16);
                }
                catch(IOException v9) {
                    throw new GdxRuntimeException("Error parsing external tileset.");
                }
            }
            else {
                v16 = element.getChildByName("image").getAttribute("source");
                v17 = element.getChildByName("image").getIntAttribute("width", 0);
                v15 = element.getChildByName("image").getIntAttribute("height", 0);
                v14 = TmxMapLoader.getRelativeFileHandle(tmxFile, v16);
            }

            TextureRegion v4 = imageResolver.getImage(v14.path());
            TiledMapTileSet v32 = new TiledMapTileSet();
            MapProperties v23 = v32.getProperties();
            v32.setName(v20);
            v23.put("firstgid", Integer.valueOf(v10));
            v23.put("imagesource", v16);
            v23.put("imagewidth", Integer.valueOf(v17));
            v23.put("imageheight", Integer.valueOf(v15));
            v23.put("tilewidth", Integer.valueOf(v7));
            v23.put("tileheight", Integer.valueOf(v8));
            v23.put("margin", Integer.valueOf(v19));
            v23.put("spacing", Integer.valueOf(v25));
            int v27 = v4.getRegionWidth() - v7;
            int v26 = v4.getRegionHeight() - v8;
            int v12 = v10;
            int v6 = v19;
            while(v6 <= v26) {
                int v5 = v19;
                int v13;
                for(v13 = v12; v5 <= v27; ++v13) {
                    TextureRegion v3 = new TextureRegion(v4, v5, v6, v7, v8);
                    if(!this.yUp) {
                        v3.flip(false, true);
                    }

                    StaticTiledMapTile v29 = new StaticTiledMapTile(v3);
                    v29.setId(v13);
                    v32.putTile(v13, v29);
                    v5 += v7 + v25;
                }

                v6 += v8 + v25;
                v12 = v13;
            }

            Iterator v11 = element.getChildrenByName("tile").iterator();
            while(true) {
                if(!v11.hasNext()) {
                    break;
                }

                Object v30 = v11.next();
                TiledMapTile v29_1 = v32.getTile(v10 + v30.getIntAttribute("id", 0));
                if(v29_1 == null) {
                    continue;
                }

                String v28 = v30.getAttribute("terrain", null);
                if(v28 != null) {
                    v29_1.getProperties().put("terrain", v28);
                }

                String v21 = v30.getAttribute("probability", null);
                if(v21 != null) {
                    v29_1.getProperties().put("probability", v21);
                }

                v22 = v30.getChildByName("properties");
                if(v22 == null) {
                    continue;
                }

                this.loadProperties(v29_1.getProperties(), v22);
            }

            v22 = element.getChildByName("properties");
            if(v22 != null) {
                this.loadProperties(v32.getProperties(), v22);
            }

            map.getTileSets().addTileSet(v32);
        }
    }

    protected TiledMap loadTilemap(Element root, FileHandle tmxFile, ImageResolver imageResolver) {
        TiledMap v7 = new TiledMap();
        String v10 = root.getAttribute("orientation", null);
        int v12 = root.getIntAttribute("width", 0);
        int v9 = root.getIntAttribute("height", 0);
        int v16 = root.getIntAttribute("tilewidth", 0);
        int v15 = root.getIntAttribute("tileheight", 0);
        String v8 = root.getAttribute("backgroundcolor", null);
        MapProperties v11 = v7.getProperties();
        if(v10 != null) {
            v11.put("orientation", v10);
        }

        v11.put("width", Integer.valueOf(v12));
        v11.put("height", Integer.valueOf(v9));
        v11.put("tilewidth", Integer.valueOf(v16));
        v11.put("tileheight", Integer.valueOf(v15));
        if(v8 != null) {
            v11.put("backgroundcolor", v8);
        }

        this.mapTileWidth = v16;
        this.mapTileHeight = v15;
        this.mapWidthInPixels = v12 * v16;
        this.mapHeightInPixels = v9 * v15;
        Element v14 = root.getChildByName("properties");
        if(v14 != null) {
            this.loadProperties(v7.getProperties(), v14);
        }

        Iterator v5 = root.getChildrenByName("tileset").iterator();
        while(v5.hasNext()) {
            Object v3 = v5.next();
            this.loadTileSet(v7, ((Element)v3), tmxFile, imageResolver);
            root.removeChild(((Element)v3));
        }

        int v4 = 0;
        int v6 = root.getChildCount();
        while(v4 < v6) {
            Element v3_1 = root.getChild(v4);
            String v13 = v3_1.getName();
            if(v13.equals("layer")) {
                this.loadTileLayer(v7, v3_1);
            }
            else if(v13.equals("objectgroup")) {
                this.loadObjectGroup(v7, v3_1);
            }

            ++v4;
        }

        return v7;
    }

    protected Array loadTilesets(Element root, FileHandle tmxFile) throws IOException {
        FileHandle v1;
        Array v3 = new Array();
        Iterator v0 = root.getChildrenByName("tileset").iterator();
        while(v0.hasNext()) {
            Object v5 = v0.next();
            String v4 = ((Element)v5).getAttribute("source", null);
            if(v4 != null) {
                FileHandle v6 = TmxMapLoader.getRelativeFileHandle(tmxFile, v4);
                v1 = TmxMapLoader.getRelativeFileHandle(v6, this.xml.parse(v6).getChildByName("image").getAttribute("source"));
            }
            else {
                v1 = TmxMapLoader.getRelativeFileHandle(tmxFile, ((Element)v5).getChildByName("image").getAttribute("source"));
            }

            v3.add(v1);
        }

        return v3;
    }
}

