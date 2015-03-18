package com.gigigo.asv.akaawait;

import android.os.AsyncTask;

/**
 * Created by Alberto on 13/03/2015.
 * este va ser igual igual al background task xo con dos mejoras
 * Tipos T
 * Evento propio
 * posibilidad de hacer un getsticky (esto es q yo ejecute sin implementar el onfinish y que al no poder devolbverlo se lo guarda como sticky para q el coleguito
 * pueda llamar a getMyStickyValue
 */
public class AsyncExecutor<T> extends AsyncTask<Void, Void, T> {
    ActionASV action2Execute;
    T stickyReturnValue; //asv el recopetin seria tenerlo con un manager
    // public InputMethodSession.EventCallback onFinish;
    public Event4Raise<T> MyOwnEvent4RaiseIT;


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

    public AsyncExecutor Execute(ActionASV act) {
        action2Execute =   act;
        this.execute();
        return this;
    }

    @Override
    protected T doInBackground(Void... YoSeriaelAction) {
        T resultT = (T)action2Execute.Execute();
        return resultT;
    }

    @Override
    protected void onPostExecute(T result) {
        try {
            if (MyOwnEvent4RaiseIT != null)
                MyOwnEvent4RaiseIT.onEvent(result);
            else
                stickyReturnValue = result;

        } catch (Exception e) {

            if (MyOwnEvent4RaiseIT != null)
                MyOwnEvent4RaiseIT.onError((T) e);
        }
    }

    public T getStickyReturnValue() {
        //asv la muvi aqui seria tener un exechuter Manager
        return stickyReturnValue;
    }

    @Override
    protected void onCancelled() {

        if (MyOwnEvent4RaiseIT != null)
            MyOwnEvent4RaiseIT = null;

    }


}



