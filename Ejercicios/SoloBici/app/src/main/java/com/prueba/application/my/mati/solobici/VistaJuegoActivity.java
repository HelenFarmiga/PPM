package com.prueba.application.my.mati.solobici;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Vector;

public class VistaJuegoActivity extends View {

	private Vector<Grafico> Coches;
	private int numCoches = 10;
	private int numMotos = 3;
	private Grafico bici;
	private int giroBici;
	private float aceleracionBici;
	private static final int PASO_GIRO_BICI = 5;
	private static final float PASO_ACELERACION_BICI = 0.5f;
	private HiloJuego hiloJuego;
	private static int PERIODO_PROCESO = 10;
	private long ultimoProceso = 0;
	
	public VistaJuegoActivity(Context contexto, AttributeSet atributos) {
		super(contexto, atributos);
		Drawable graficoBici, graficoCoche, graficoRueda;
		graficoCoche = contexto.getResources().getDrawable(R.drawable.coche);


		Coches = new Vector<Grafico>();
		for (int i=0; i<numCoches; i++) {
			Grafico coche = new Grafico(this, graficoCoche);
			coche.setIncX(Math.random() * 8 - 2);
			coche.setIncY(Math.random() * 8 - 2);
			coche.setAngulo((int) (Math.random() * 360));
			coche.setRotacion((int) (Math.random() * 8 - 4));
			Coches.add(coche);
		}

		graficoBici = contexto.getResources().getDrawable(R.drawable.bici);
		bici = new Grafico(this, graficoBici);
        bici.setIncX(Math.random() * 10 - 2);
        bici.setIncY(Math.random() * 10 - 2);
        bici.setAngulo((int) (Math.random() * 360));
        bici.setRotacion((int) (Math.random() * 8 - 4));
	}


	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		for (Grafico coche: Coches) {
			do {
				coche.setPosX(Math.random()*(w-coche.getAncho()));
				coche.setPosY(Math.random()*(h-coche.getAlto()));
			} while (coche.distancia(bici) < (w+h)/5);
		}

        bici.setPosX(((w/2)-bici.getAncho()));
        bici.setPosY(((h/2)-bici.getAlto()));

        hiloJuego = new HiloJuego();
        hiloJuego.start();
		
	}

    protected synchronized void actualizaMovimiento(){
        long ahora = System.currentTimeMillis();

        if(ultimoProceso + PERIODO_PROCESO > ahora){
            return;
        }

        double retardo = (ahora - ultimoProceso) / PERIODO_PROCESO;

        bici.setAngulo((int) (bici.getAngulo() + giroBici * retardo));
        double nIncX = bici.getIncX() + aceleracionBici * Math.cos(Math.toRadians(bici.getAngulo())) * retardo;
        double nIncY = bici.getIncY() + aceleracionBici * Math.sin(Math.toRadians(bici.getAngulo())) * retardo;

        if (Grafico.distanciaE(0, 0, nIncX, nIncY)<= Grafico.getMaxVelocidad()) {
            bici.setIncX(nIncX);
            bici.setIncY(nIncY);
        }

        bici.incrementaPos();

        for(Grafico coche : Coches){
            coche.incrementaPos();
        }

        ultimoProceso = ahora;

    }
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (Grafico coche: Coches)
			coche.dibujaGrafico(canvas);
        bici.dibujaGrafico(canvas);

	}

    private class HiloJuego extends Thread{
        @Override
        public void run(){
            while(true)
                actualizaMovimiento();
        }
    }

}

