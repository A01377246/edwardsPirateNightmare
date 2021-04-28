package mx.itesm.emerald.edwardspiratenightmare;

import com.badlogic.gdx.graphics.Texture;

public class Corazon extends Objeto {

    public Corazon(Texture textura, float x, float y) {
        super(textura, x, y);
    }

    public float getX() {
        return sprite.getX();
    }
}
