package mx.itesm.emerald.edwardspiratenightmare;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PantallaTienda extends Pantalla {
    private Texture tienda;
    private Texture texturaVolver;
    private Stage escenaTienda;

    public PantallaTienda(final EdwardsPirateNightmare juego) {
        tienda = new Texture("pantallas/empty store.png");
        escenaTienda = new Stage(vista); // vista como paramétro para que se escalen correctamente

        texturaVolver = new Texture("botones/botonvolverS.png");
        TextureRegionDrawable trdBtnVolver = new TextureRegionDrawable(texturaVolver);
        Button botonVolver = new Button(trdBtnVolver);
        botonVolver.setPosition(0, 0);
        botonVolver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Cambiar pantalla
                juego.setScreen(new PantallaMenu(juego));
            }
        });
        escenaTienda.addActor(botonVolver);
        Gdx.input.setInputProcessor(escenaTienda);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.setProjectionMatrix(camara.combined);
        batch.draw(tienda, 0, 0);
        batch.end();
        escenaTienda.draw();
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




