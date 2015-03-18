package com.gigigo.asv.akaawait;

import android.os.AsyncTask;
import android.view.inputmethod.InputMethodSession;

/**
 * Created by Alberto on 12/03/2015.
 */
public class BackgroundExecutor extends AsyncTask<Void, Void, String> {
    ActionASV actionASV;
    public InputMethodSession.EventCallback onFinish;
    public InputMethodSession.EventCallback onFuckingError;
    public InputMethodSession.EventCallback onWhatever;

//asv esto es un invocador,  ejeutador o nigromante :P

    //test2
   // Handler.Callback ChorcheNager_Volvere;
    //test3
    //job
    //test3
    // runnable
    /*Lo siguiente serviria para 2 cosas:
    * 1º pasarle la complejidad de la gestion de los eventos a través Eventbus/otto y crear siempre un evento
    * de vuelta q devuelva T
    * 2ºPara hacer una clase base de intent capaz de poner de extras objetos complejos a partir de busevente con tipos T*/
    //http://es.slideshare.net/greenrobot/eventbus-for-android-15314813
    //test4 y mejor
    //eventbus
    //test5 busotto
    // otto(bus)

    public BackgroundExecutor Execute(ActionASV act) {

        actionASV = act;
        this.execute();

        return this;
    }

    @Override
    protected String doInBackground(Void... YoSeriaelAction) {

        return actionASV.Execute();
    }

    @Override
    protected void onPostExecute(String result) {
        try {


            if (onFinish != null)
                onFinish.finishedEvent(1, true);


        } catch (Exception e) {

        }
    }

    @Override
    protected void onCancelled() {

        this.onFinish = null;
    }


}


