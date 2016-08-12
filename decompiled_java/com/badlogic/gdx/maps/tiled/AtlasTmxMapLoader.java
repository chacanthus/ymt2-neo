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
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture$TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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

public class AtlasTmxMapLoader extends AsynchronousAssetLoader {
    abstract interface AtlasResolver {
        public abstract TextureAtlas getAtlas(String arg0);
    }

    public class AtlasTiledMapLoaderParameters extends AssetLoaderParameters {
        public boolean convertObjectToTileSpace;
        public boolean forceTextureFilters;
        public TextureFilter textureMagFilter;
        public TextureFilter textureMinFilter;
        public boolean yUp;

        public AtlasTiledMapLoaderParameters() {
            super();
            this.yUp = true;
            this.forceTextureFilters = false;
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
    protected Array trackedTextures;
    protected XmlReader xml;
    protected boolean yUp;

    public AtlasTmxMapLoader() {
        super(new InternalFileHandleResolver());
        this.xml = new XmlReader();
        this.trackedTextures = new Array();
    }

    public AtlasTmxMapLoader(FileHandleResolver resolver) {
        super(resolver);
        this.xml = new XmlReader();
        this.trackedTextures = new Array();
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
        return this.getDependencies(x0, x1, ((AtlasTiledMapLoaderParameters)x2));
    }

    public Array getDependencies(String fileName, FileHandle tmxFile, AtlasTiledMapLoaderParameters parameter) {  // has try-catch handlers
        Array v1 = new Array();
        try {
            this.root = this.xml.parse(tmxFile);
            Element v5 = this.root.getChildByName("properties");
            if(v5 != null) {
                Iterator v3 = v5.getChildrenByName("property").iterator();
                while(v3.hasNext()) {
                    Object v6 = v3.next();
                    String v4 = ((Element)v6).getAttribute("name");
                    String v7 = ((Element)v6).getAttribute("value");
                    if(!v4.startsWith("atlas")) {
                        continue;
                    }

                    v1.add(new AssetDescriptor(AtlasTmxMapLoader.getRelativeFileHandle(tmxFile, v7), TextureAtlas.class));
                }
            }
        }
        catch(IOException v2) {
            throw new GdxRuntimeException("Unable to parse .tmx file.");
        }

        return v1;
    }

    public static FileHandle getRelativeFileHandle(FileHandle file, String path) {
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
        return this.load(fileName, new AtlasTiledMapLoaderParameters());
    }

    public TiledMap load(String fileName, AtlasTiledMapLoaderParameters parameter) {  // has try-catch handlers
        if(parameter != null) {
            try {
                this.yUp = parameter.yUp;
                this.convertObjectToTileSpace = parameter.convertObjectToTileSpace;
                goto label_5;
            label_27:
                this.yUp = true;
                this.convertObjectToTileSpace = false;
            label_5:
                FileHandle v6 = this.resolve(fileName);
                this.root = this.xml.parse(v6);
                ObjectMap v3 = new ObjectMap();
                FileHandle v1 = this.loadAtlas(this.root, v6);
                if(v1 == null) {
                    throw new GdxRuntimeException("Couldn\'t load atlas");
                }

                v3.put(v1.path(), new TextureAtlas(v1));
                TiledMap v5 = this.loadMap(this.root, v6, new DirectAtlasResolver(v3), parameter);
                v5.setOwnedResources(v3.values().toArray());
                this.setTextureFilters(parameter.textureMinFilter, parameter.textureMagFilter);
                return v5;
            }
            catch(IOException v4) {
                throw new GdxRuntimeException("Couldn\'t load tilemap \'" + fileName + "\'", ((Throwable)v4));
            }
        }
        else {
            goto label_27;
        }

        goto label_5;
    }

    public void loadAsync(AssetManager x0, String x1, FileHandle x2, AssetLoaderParameters x3) {
        this.loadAsync(x0, x1, x2, ((AtlasTiledMapLoaderParameters)x3));
    }

    public void loadAsync(AssetManager manager, String fileName, FileHandle tmxFile, AtlasTiledMapLoaderParameters parameter) {  // has try-catch handlers
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
            this.map = this.loadMap(this.root, tmxFile, new AssetManagerAtlasResolver(manager), parameter);
            return;
        }
        catch(Exception v0) {
            throw new GdxRuntimeException("Couldn\'t load tilemap \'" + fileName + "\'", ((Throwable)v0));
        }
    }

    protected FileHandle loadAtlas(Element root, FileHandle tmxFile) throws IOException {
        FileHandle v5_1;
        String v4;
        String v5 = null;
        Element v0 = root.getChildByName("properties");
        if(v0 != null) {
            Iterator v1 = v0.getChildrenByName("property").iterator();
            do {
                if(v1.hasNext()) {
                    Object v3 = v1.next();
                    String v2 = ((Element)v3).getAttribute("name", v5);
                    v4 = ((Element)v3).getAttribute("value", v5);
                    if(!v2.equals("atlas")) {
                        continue;
                    }

                    if(v4 == null) {
                        v4 = ((Element)v3).getText();
                    }

                    if(v4 == null) {
                        continue;
                    }

                    if(v4.length() == 0) {
                        continue;
                    }

                    break;
                }

                goto label_23;
            }
            while(true);

            v5_1 = AtlasTmxMapLoader.getRelativeFileHandle(tmxFile, v4);
        }

    label_23:
        return v5_1;
    }

    protected TiledMap loadMap(Element root, FileHandle tmxFile, AtlasResolver resolver, AtlasTiledMapLoaderParameters parameter) {
        TiledMap v3 = new TiledMap();
        String v13 = root.getAttribute("orientation", null);
        int v15 = root.getIntAttribute("width", 0);
        int v12 = root.getIntAttribute("height", 0);
        int v17 = root.getIntAttribute("tilewidth", 0);
        int v16 = root.getIntAttribute("tileheight", 0);
        String v11 = root.getAttribute("backgroundcolor", null);
        MapProperties v14 = v3.getProperties();
        if(v13 != null) {
            v14.put("orientation", v13);
        }

        v14.put("width", Integer.valueOf(v15));
        v14.put("height", Integer.valueOf(v12));
        v14.put("tilewidth", Integer.valueOf(v17));
        v14.put("tileheight", Integer.valueOf(v16));
        if(v11 != null) {
            v14.put("backgroundcolor", v11);
        }

        this.mapTileWidth = v17;
        this.mapTileHeight = v16;
        this.mapWidthInPixels = v15 * v17;
        this.mapHeightInPixels = v12 * v16;
        int v9 = 0;
        int v10 = root.getChildCount();
        while(v9 < v10) {
            Element v4 = root.getChild(v9);
            String v8 = v4.getName();
            if(v8.equals("properties")) {
                this.loadProperties(v3.getProperties(), v4);
            }
            else if(v8.equals("tileset")) {
                this.loadTileset(v3, v4, tmxFile, resolver, parameter);
            }
            else if(v8.equals("layer")) {
                this.loadTileLayer(v3, v4);
            }
            else if(v8.equals("objectgroup")) {
                this.loadObjectGroup(v3, v4);
            }

            ++v9;
        }

        return v3;
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

    public TiledMap loadSync(AssetManager manager, String fileName, FileHandle file, AtlasTiledMapLoaderParameters parameter) {
        if(parameter != null) {
            this.setTextureFilters(parameter.textureMinFilter, parameter.textureMagFilter);
        }

        return this.map;
    }

    public Object loadSync(AssetManager x0, String x1, FileHandle x2, AssetLoaderParameters x3) {
        return this.loadSync(x0, x1, x2, ((AtlasTiledMapLoaderParameters)x3));
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

    protected void loadTileset(TiledMap map, Element element, FileHandle tmxFile, AtlasResolver resolver, AtlasTiledMapLoaderParameters parameter) {  // has try-catch handlers
        Element v16;
        Iterator v7;
        int v9;
        int v11;
        String v10;
        if(element.getName().equals("tileset")) {
            String v14 = element.get("name", null);
            int v6 = element.getIntAttribute("firstgid", 1);
            int v31 = element.getIntAttribute("tilewidth", 0);
            int v28 = element.getIntAttribute("tileheight", 0);
            int v22 = element.getIntAttribute("spacing", 0);
            int v13 = element.getIntAttribute("margin", 0);
            String v21 = element.getAttribute("source", null);
            if(v21 != null) {
                FileHandle v32 = AtlasTmxMapLoader.getRelativeFileHandle(tmxFile, v21);
                try {
                    element = this.xml.parse(v32);
                    v14 = element.get("name", null);
                    v31 = element.getIntAttribute("tilewidth", 0);
                    v28 = element.getIntAttribute("tileheight", 0);
                    v22 = element.getIntAttribute("spacing", 0);
                    v13 = element.getIntAttribute("margin", 0);
                    v10 = element.getChildByName("image").getAttribute("source");
                    v11 = element.getChildByName("image").getIntAttribute("width", 0);
                    v9 = element.getChildByName("image").getIntAttribute("height", 0);
                }
                catch(IOException v5) {
                    throw new GdxRuntimeException("Error parsing external tileset.");
                }
            }
            else {
                v10 = element.getChildByName("image").getAttribute("source");
                v11 = element.getChildByName("image").getIntAttribute("width", 0);
                v9 = element.getChildByName("image").getIntAttribute("height", 0);
            }

            if(!map.getProperties().containsKey("atlas")) {
                throw new GdxRuntimeException("The map is missing the \'atlas\' property");
            }

            FileHandle v4 = this.resolve(AtlasTmxMapLoader.getRelativeFileHandle(tmxFile, map.getProperties().get("atlas", String.class)).path());
            TextureAtlas v3 = resolver.getAtlas(v4.path());
            String v20 = v4.nameWithoutExtension();
            if(parameter != null && (parameter.forceTextureFilters)) {
                v7 = v3.getTextures().iterator();
                while(v7.hasNext()) {
                    this.trackedTextures.add(v7.next());
                }
            }

            TiledMapTileSet v30 = new TiledMapTileSet();
            MapProperties v17 = v30.getProperties();
            v30.setName(v14);
            v17.put("firstgid", Integer.valueOf(v6));
            v17.put("imagesource", v10);
            v17.put("imagewidth", Integer.valueOf(v11));
            v17.put("imageheight", Integer.valueOf(v9));
            v17.put("tilewidth", Integer.valueOf(v31));
            v17.put("tileheight", Integer.valueOf(v28));
            v17.put("margin", Integer.valueOf(v13));
            v17.put("spacing", Integer.valueOf(v22));
            v7 = v3.findRegions(v20).iterator();
            while(true) {
                if(!v7.hasNext()) {
                    break;
                }

                Object v18 = v7.next();
                if(v18 == null) {
                    continue;
                }

                StaticTiledMapTile v25 = new StaticTiledMapTile(v18);
                if(!this.yUp) {
                    v18.flip(false, true);
                }

                int v29 = v6 + v18.index;
                v25.setId(v29);
                v30.putTile(v29, v25);
            }

            v7 = element.getChildrenByName("tile").iterator();
            while(true) {
                if(!v7.hasNext()) {
                    break;
                }

                Object v26 = v7.next();
                TiledMapTile v25_1 = v30.getTile(v6 + v26.getIntAttribute("id", 0));
                if(v25_1 == null) {
                    continue;
                }

                String v23 = v26.getAttribute("terrain", null);
                if(v23 != null) {
                    v25_1.getProperties().put("terrain", v23);
                }

                String v15 = v26.getAttribute("probability", null);
                if(v15 != null) {
                    v25_1.getProperties().put("probability", v15);
                }

                v16 = v26.getChildByName("properties");
                if(v16 == null) {
                    continue;
                }

                this.loadProperties(v25_1.getProperties(), v16);
            }

            v16 = element.getChildByName("properties");
            if(v16 != null) {
                this.loadProperties(v30.getProperties(), v16);
            }

            map.getTileSets().addTileSet(v30);
        }
    }

    private void setTextureFilters(TextureFilter min, TextureFilter mag) {
        Iterator v0 = this.trackedTextures.iterator();
        while(v0.hasNext()) {
            v0.next().setFilter(min, mag);
        }
    }
}

