package mx.itesm.emerald.edwardspiratenightmare;

import com.badlogic.gdx.graphics.Texture;

public class Corazon extends Objeto {

    //Animación
    private Texture texturaExplota;
    private EstadoCorazon estado;

    public Corazon(Texture textura, float x, float y) {
        super(textura, x, y);
        estado = EstadoCorazon.EXPLOTA;
    }

    //public Corazon(Texture TexturaNormal, Texture texturaExplota, float x, float y) {
    //super(TexturaNormal, x, y);
    //this.texturaExplota = texturaExplota;
    //estado = EstadoCorazon.NORMAL;
    //}

    public void setEstado(EstadoCorazon nuevoEstado) {
        this.estado = nuevoEstado;
        //if (nuevoEstado == EstadoAlien.EXPLOTA) {
        //sprite.setTexture(texturaExplota);
        //}
    }

    public EstadoCorazon getEstado() {
        return estado;
    }

    public float getX() {
        return sprite.getX();
    }
}
