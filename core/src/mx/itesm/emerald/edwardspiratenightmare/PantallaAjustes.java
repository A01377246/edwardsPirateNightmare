package mx.itesm.emerald.edwardspiratenightmare;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PantallaAjustes extends Pantalla {
    private Texture fondoPantallaAjustes;
    private Texture botonBrillo;
    private Texture botonsinMusica;
    private Stage escenaAjustes;

    public PantallaAjustes(final EdwardsPirateNightmare juego) {
        escenaAjustes= new Stage(vista);
        fondoPantallaAjustes = new Texture("pantallas/lvl1.png");
        Button botonMusica = crearBoton("sprites/headphones.png");
        botonMusica.setPosition(ANCHO /3, ALTO - 200);
        Button botonVolver = crearBoton("botones/botonVolverS.png"); // cargar imágen del botón
        botonVolver.setPosition(0, 25);
        botonVolver.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                    // volver al menú principal
                juego.setScreen(new PantallaMenu(juego));
            }
        });

        /*botonMusica.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
        */
        escenaAjustes.addActor(botonMusica); // añadir boton musica a escena
        escenaAjustes.addActor(botonVolver);
        Gdx.input.setInputProcessor(escenaAjustes); // la escena juego se encarga de registar el inpu



    }

    @Override
    public void show() {


    }



    @Override
    public void render(float delta) {
        batch.begin();
        batch.setProjectionMatrix(camara.combined);

        batch.draw(fondoPantallaAjustes,0,0);
        escenaAjustes.draw(); // dibujar escena de la pantalla ajustes


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

    private Button crearBoton(String imagen) { // añadir segundo parámetro para hacer un cambio cuando el usuario da click
        // crear otra textura y otro drawable
        Texture texturaBoton = new Texture(imagen); // cargar imagén del botón.
        TextureRegionDrawable trdBtnJugar = new TextureRegionDrawable(texturaBoton); // tipo correcto del boton, drawable. No acepta texture


        return new Button(trdBtnJugar); // regesar imagen normal e imagen de retroalimentacion
    }
}
