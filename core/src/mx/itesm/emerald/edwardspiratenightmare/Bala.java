package mx.itesm.emerald.edwardspiratenightmare;

import com.badlogic.gdx.graphics.Texture;

public class Bala extends Objeto {
    private float vX = 500;

    public Bala(Texture textura, float x, float y) {
        super(textura, x, y);
    }

    //Mover a la derecha bala
    public void mover(float delta) {
        float dx = vX * delta;
        sprite.setX(sprite.getX() + dx);
        //sprite.setY(120);
    }

    public float getX() {
        return sprite.getX();
    }
}
