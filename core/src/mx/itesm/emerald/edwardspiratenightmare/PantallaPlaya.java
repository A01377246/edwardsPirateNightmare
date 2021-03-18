package mx.itesm.emerald.edwardspiratenightmare;

import com.badlogic.gdx.graphics.Texture;

public class PantallaPlaya extends Pantalla {
    private EdwardsPirateNightmare juego;
    private Texture fondoPlaya;
    private Edward edward;

    public PantallaPlaya(EdwardsPirateNightmare juego) {
        this.juego = juego;
        fondoPlaya = new Texture("Pantallas/lvl1.png");
    }
    // Crear al personaje principal
    private void crearEdward(){
        Texture texturaEdward = new Texture("sprites/edwardFront.png");
        edward = new Edward(texturaEdward,0,200);

    }
    @Override
    public void show() {
        crearEdward();

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.setProjectionMatrix(camara.combined);
        batch.draw(fondoPlaya,0,0);
        edward.render(batch);
        batch.end();

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
