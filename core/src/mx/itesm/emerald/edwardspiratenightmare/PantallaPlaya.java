package mx.itesm.emerald.edwardspiratenightmare;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import mx.itesm.emerald.edwardspiratenightmare.utilities.Texto;

public class PantallaPlaya extends Pantalla {
    private EdwardsPirateNightmare juego;

    //Fondo infinito
    private Texture texturaFPlaya;
    private float xFondo = 0;

    //Personaje
    private Edward edward;
    private Texture texturaEdward;

    //Fantasma 1 (Enemigos)
    private Array<Fantasma1> arrFantasma1;
    private Texture texturaFantasma1;
    private float timerCrearFantasma1;
    private final float TIEMPO_CREAR_FANTASMA1 = 3;

    //Crear Marcador
    private int puntos = 0;
    private Texto texto;

    //Disparo del personaje
    private Array<Bala> arrBalas;
    private Texture texturaBala;

    //Vidas del personaje
    private Array<Corazon> arrCorazones;
    private int iVidas = 4;

    public PantallaPlaya(EdwardsPirateNightmare juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        crearFondo();
        crearTexto();
        crearEdward();
        crearBalas();
        crearCorazon();
        crearFantasma1();
        //Pone el input Processor
        Gdx.input.setInputProcessor(new ProcesarEntrada());
    }

    private void crearCorazon() {
        Texture texturaCorazon = new Texture("sprites/heart.png");
        arrCorazones = new Array<>(5);
        for (int colunma = 0; colunma < 5; colunma++) {
            Corazon corazon = new Corazon(texturaCorazon,
                    50 + colunma * 60, 0.88f * ALTO);
            arrCorazones.add(corazon);
        }
    }

    private void crearBalas() {
        arrBalas = new Array<>();
        texturaBala = new Texture("sprites/bala.png");
    }

    private void crearFondo() {
        texturaFPlaya = new Texture("pantallas/P1.png");
    }

    private void crearTexto() {
        texto = new Texto("fonts/pirate.fnt");
    }

    private void crearFantasma1() {
        arrFantasma1 = new Array<>();
        texturaFantasma1 = new Texture("sprites/fantasma1/fantasmaAnimacion.png");
    }

    private void crearEdward() {
        texturaEdward = new Texture("sprites/edwardRun.png");
        edward = new Edward(texturaEdward, 20, 50);
    }

    @Override
    public void render(float delta) {
        //Actualizar
        actualizar(delta);
        batch.begin();
        batch.setProjectionMatrix(camara.combined);
        //Dibuja Fondo
        batch.draw(texturaFPlaya, xFondo, 0);
        batch.draw(texturaFPlaya, xFondo + texturaFPlaya.getWidth(), 0);
        //Dibuja personaje
        edward.render(batch);
        //Dibujar marcador
        texto.mostrarMensaje(batch, Integer.toString(puntos), 0.95f * ANCHO, 0.95f * ALTO);
        //Dibujar corazon
        for (Corazon corazon : arrCorazones) {
            corazon.render(batch);
        }
        //Dibujar fantasma1
        for (Fantasma1 fantasma1 : arrFantasma1) {
            fantasma1.render(batch);
        }
        //Dibujar balas
        for (Bala bala : arrBalas) {
            bala.render(batch);
        }
        batch.end();
    }

    private void actualizar(float delta) {
        actualizarFondo();
        actualizarFantasma1(delta);
        actualizarBalas(delta);
    }

    private void actualizarBalas(float delta) {
        for (int i = arrBalas.size - 1; i >= 0; i--) {
            Bala bala = arrBalas.get(i);
            bala.mover(delta);
            //prueba si la bola debe de desaparecer si se sale de la pantalla
            if (bala.getX() > ANCHO) {
                arrBalas.removeIndex(i);
            } else {
                for (int iA = arrFantasma1.size - 1; iA >= 0; iA--) {
                    Fantasma1 fantasma1 = arrFantasma1.get(iA);
                    if (bala.sprite.getBoundingRectangle().overlaps(fantasma1.sprite.getBoundingRectangle())) {
                        //Contar Puntos
                        puntos += 150;
                        //Borrar bala
                        arrBalas.removeIndex(i);
                        arrFantasma1.removeIndex(iA);
                        break;
                    }
                }
            }
        }
    }

    private void actualizarFantasma1(float delta) {
        timerCrearFantasma1 += delta;
        if (timerCrearFantasma1 > TIEMPO_CREAR_FANTASMA1) {
            timerCrearFantasma1 = 0;
            //crear fantasma1
            float xFantasma1 = MathUtils.random(ANCHO, ANCHO * 1.5f);
            float yFantasma1 = MathUtils.random(20, 250);
            Fantasma1 fantasma1 = new Fantasma1(texturaFantasma1, xFantasma1, yFantasma1);
            arrFantasma1.add(fantasma1);
        }

        //Mover al fantasma1
        for (Fantasma1 fantasma1 : arrFantasma1) {
            fantasma1.moverIzquierda(delta);
        }

        for (int i = arrFantasma1.size - 1; i >= 0; i--) {
            Fantasma1 fantasma1 = arrFantasma1.get(i);
            if (edward.sprite.getBoundingRectangle().overlaps(fantasma1.sprite.getBoundingRectangle())) {
                arrFantasma1.removeIndex(i);
                arrCorazones.removeIndex(iVidas);
                iVidas = iVidas - 1;
                break;
                //Corazon corazon = arrCorazones.get(iVidas);
                //corazon.setEstado(EstadoCorazon.EXPLOTA);
            }
        }
    }

    private void actualizarFondo() {
        xFondo -= 4;
        if (xFondo <= -texturaFPlaya.getWidth()) {
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
        arrCorazones.clear();
        arrFantasma1.clear();
        arrBalas.clear();
    }

    private class ProcesarEntrada implements InputProcessor {

        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            Vector3 v = new Vector3(screenX, screenY, 0);
            camara.unproject(v);
            if (v.x >= ANCHO / 2) {
                //Dispara
                Bala bala = new Bala(texturaBala, edward.getSprite().getX(), edward.getSprite().getY());
                arrBalas.add(bala);
            } else {
                edward.saltar();
            }
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(float amountX, float amountY) {
            return false;
        }
    }
}



