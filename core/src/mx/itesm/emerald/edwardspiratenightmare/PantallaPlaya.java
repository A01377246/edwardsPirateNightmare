package mx.itesm.emerald.edwardspiratenightmare;

import com.badlogic.gdx.graphics.Texture;

import mx.itesm.emerald.edwardspiratenightmare.utilities.Texto;

public class PantallaPlaya extends Pantalla {

    private EdwardsPirateNightmare juego;
    //Fondo infinito
    private Texture texturaFPlaya;
    private float xFondo = 0;

    //Personaje
    private Edward edward;

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
        actualizar();

        batch.begin();
        batch.setProjectionMatrix(camara.combined);

        //Dibuja Fondo
        batch.draw(texturaFPlaya,xFondo,0);
        batch.draw(texturaFPlaya,xFondo+texturaFPlaya.getWidth(),0);

        //Dibuja personaje
        edward.render(batch);

        //Dibujar marcador
        texto.mostrarMensaje(batch,Integer.toString(puntos),0.95f*ANCHO,0.95f*ALTO);

        batch.end();

    }

    private void actualizar() {
        actualizarFondo();
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



