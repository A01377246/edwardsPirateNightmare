package mx.itesm.emerald.edwardspiratenightmare;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import mx.itesm.emerald.edwardspiratenightmare.Objeto;

public class Fantasma1 extends Objeto
{
    private Animation<TextureRegion> animacion;
    private float timerAnimacion;

    //Fisica
    private float velocidadX = -400;

    public Fantasma1(Texture textura,float x, float y)
    {
        TextureRegion region = new TextureRegion(textura);
        TextureRegion [][] texturas = region.split(128,128);

        //Animacion
        TextureRegion[] arrFrames = {texturas[0][1],texturas[0][2],texturas[0][3],texturas[0][4]};
        animacion = new Animation<>(0.2f, arrFrames);
        animacion.setPlayMode(Animation.PlayMode.LOOP);
        timerAnimacion = 0;

        //IDLE
        sprite = new Sprite(texturas[0][0]);
        sprite.setPosition(x,y);
    }

    @Override
    public void render(SpriteBatch batch) {
        timerAnimacion += Gdx.graphics.getDeltaTime();
        TextureRegion frame = animacion.getKeyFrame(timerAnimacion);
        batch.draw(frame,sprite.getX(),sprite.getY());
    }

    //Mover enemigo
    public void moverIzquierda(float delta)
    {
        float dx = velocidadX * delta;
        sprite.setX(sprite.getX()+dx);
    }

    public float getX(){
        return sprite.getX();
    }
    public float getY(){
        return sprite.getY();
    }
}
