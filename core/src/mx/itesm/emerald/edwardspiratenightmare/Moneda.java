package mx.itesm.emerald.edwardspiratenightmare;

// Esta clase representa las monedas que el jugador puede recoger para comprar objetos.

import com.badlogic.gdx.graphics.Texture;

public class Moneda extends Objeto {

    private final float velocidadX = -400; // velocidad de las monedas al desplazarse a la izquieda de la pantalla

    public Moneda(Texture texturaMoneda, float x, float y) {
        super(texturaMoneda, x, y);
    }

    // Recibe cómo parámetro el tiempo que ha transcurrido en el juego y lo usa para calcular
    public void moverIzquierda(float delta) {
        // la distancia que recorre la moneda
        float dx = velocidadX * delta; // ecuación del desplazamiento o distancia
        sprite.setX(sprite.getX() + dx); // mover la moneda
    }

    public float getX() {
        return sprite.getX();
    }
}
