package mx.itesm.emerald.edwardspiratenightmare;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import mx.itesm.emerald.edwardspiratenightmare.utilities.Texto;

public class PantallaPlaya extends Pantalla {

    private EdwardsPirateNightmare juego;
    //Fondo infinito
    private Texture texturaFPlaya;
    private float xFondo = 0;

    //Personaje
    private Edward edward;

    //Fantasma 1 (Enemigos)
    private Array<Fantasma1> arrFantasma1;
    private Texture texturaFantasma1;
    private float timerCrearFantasma1;
    private final float TIEMPO_CREAR_FANTASMA1 = 3;

    //Crear Marcador
    private int puntos = 0;
    private Texto texto;

    public PantallaPlaya(EdwardsPirateNightmare juego) {
        this.juego = juego;
        //fondoPlaya = new Texture("Pantallas/lvl1.png");
    }

    // Crear al personaje principal
    private void crearEdward(){
        Texture texturaEdward = new Texture("sprites/edwardFront.png");
        edward = new Edward(texturaEdward,20,50);

    }
    @Override
    public void show() {
        crearEdward();
        crearTexto();
        crearFondo();
        crearFantasma1();
    }

    private void crearFantasma1() {
        texturaFantasma1 = new Texture("sprites/fantasma1/fantasmaAnimacion.png");
        arrFantasma1 = new Array<>();
    }

    private void crearFondo() {
        texturaFPlaya = new Texture("pantallas/P1.png");
    }

    private void crearTexto() {
        texto = new Texto("fonts/pirate.fnt");
    }

    @Override
    public void render(float delta)
    {
        //Actualizar
        actualizar(delta);

        batch.begin();
        batch.setProjectionMatrix(camara.combined);

        //Dibuja Fondo
        batch.draw(texturaFPlaya,xFondo,0);
        batch.draw(texturaFPlaya,xFondo+texturaFPlaya.getWidth(),0);

        //Dibuja personaje
        edward.render(batch);

        //Dibujar marcador
        texto.mostrarMensaje(batch,Integer.toString(puntos),0.95f*ANCHO,0.95f*ALTO);

        //Dibujar fantasma1
        for (Fantasma1 fantasma1: arrFantasma1) {
            fantasma1.render(batch);
        }

        batch.end();

    }

    private void actualizar(float delta) {
        actualizarFondo();
        actualizarFantasma1(delta);
    }

    private void actualizarFantasma1(float delta) {
        timerCrearFantasma1 +=delta;
        if (timerCrearFantasma1>TIEMPO_CREAR_FANTASMA1)
        {
            timerCrearFantasma1 = 0;
            //crear fantasma1
            float xFantasma1 = MathUtils.random(ANCHO,ANCHO*1.5f);
            float yFantasma1 = MathUtils.random(20,250);
            Fantasma1 fantasma1 = new Fantasma1(texturaFantasma1,xFantasma1,yFantasma1);
            arrFantasma1.add(fantasma1);
        }

        //Mover al fantasma1
        for (Fantasma1 fantasma1: arrFantasma1) {
            fantasma1.moverIzquierda(delta);
        }
    }

    private void actualizarFondo() {
        xFondo -=4;
        if (xFondo<= -texturaFPlaya.getWidth())
        {
            xFondo = 0;
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}



