package com.gigigo.asv.akaawait;

import android.os.AsyncTask;

/**
 * Created by Alberto on 07/04/2015.
 */
public class AsyncTaskObjectModel extends AsyncTask<Void, Void, Object> {
    Action task_action2Execute;//asv Rollito marked interfake si no ;)
    public Object task_stickyReturnValue; //T u OBJECT, aun no sé q es más optimal
    public String task_name_key;
    public Event4Raise<Object> MyOwnEvent4RaiseIT;
    public Enum<Enum_Tasks_STATUS> task_status = Enum_Tasks_STATUS.NOT_RUN_YET;

    //region chorricadas
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
    //endregion

    public AsyncTaskObjectModel Execute(Action act) {
        this.task_action2Execute = act;
      //  this.task_name_key = getMyInvoker();
        this.task_status = Enum_Tasks_STATUS.PENDIENTE;
        this.task_stickyReturnValue = null;
        this.execute();
        return this;
    }

    @Override
    protected Object doInBackground(Void... nadaEnConcreto) {
        this.task_status = Enum_Tasks_STATUS.RUNNING_FREE_YEAH;
        Object resultT = (Object) task_action2Execute.Execute();
        return resultT;
    }

    @Override
    protected void onPostExecute(Object result) {
        try {

            this.task_status = Enum_Tasks_STATUS.FINITA;

            if (this.MyOwnEvent4RaiseIT != null)
                this.MyOwnEvent4RaiseIT.onEvent(result);
            else
                this.task_stickyReturnValue = result;

        } catch (Exception e) {

            if (this.MyOwnEvent4RaiseIT != null)
                this.MyOwnEvent4RaiseIT.onError((Object) e);
        }
    }

    public Object getStickyReturnValue() {
        //asv la muvi aqui seria tener un exechuter Manager
        return this.task_stickyReturnValue;
    }

    @Override
    protected void onCancelled() {

        this.task_status = Enum_Tasks_STATUS.FINITA;//asv todo igual un nuevo status para el error? aunk tb los trigger me determinan el estatus
        if (this.MyOwnEvent4RaiseIT != null)
            this.MyOwnEvent4RaiseIT = null;

    }


}

//public class AsyncTaskObjectModel<T> extends AsyncTask<Void, Void, T> {
//    Action task_action2Execute;//asv Rollito marked interfake si no ;)
//    public T task_stickyReturnValue; //T u OBJECT, aun no sé q es más optimal
//    public String task_name_key;
//    public Event4Raise<T> MyOwnEvent4RaiseIT;
//    public Enum<Enum_Tasks_STATUS> task_status = Enum_Tasks_STATUS.NOT_RUN_YET;
//
//    //region chorricadas
//    //test2
//    // Handler.Callback ChorcheNager_Volvere;
//    //test3
//    //job
//    //test3
//    // runnable
//        /*Lo siguiente serviria para 2 cosas:
//        * 1º pasarle la complejidad de la gestion de los eventos a través Eventbus/otto y crear siempre un evento
//        * de vuelta q devuelva T
//        * 2ºPara hacer una clase base de intent capaz de poner de extras objetos complejos a partir de busevente con tipos T*/
//    //http://es.slideshare.net/greenrobot/eventbus-for-android-15314813
//    //test4 y mejor
//    //eventbus
//    //test5 busotto
//    // otto(bus)
//    //endregion
//
//    public AsyncTaskObjectModel Execute(Action act) {
//        this.task_action2Execute = act;
//        this.task_name_key = getMyInvoker();
//        this.task_status = Enum_Tasks_STATUS.PENDIENTE;
//        this.task_stickyReturnValue = null;
//        this.execute();
//        return this;
//    }
//
//    @Override
//    protected T doInBackground(Void... nadaEnConcreto) {
//        this.task_status = Enum_Tasks_STATUS.RUNNING_FREE_YEAH;
//        T resultT = (T) task_action2Execute.Execute();
//        return resultT;
//    }
//
//    @Override
//    protected void onPostExecute(T result) {
//        try {
//
//            this.task_status = Enum_Tasks_STATUS.FINITA;
//
//            if (this.MyOwnEvent4RaiseIT != null)
//                this.MyOwnEvent4RaiseIT.onEvent(result);
//            else
//                this.task_stickyReturnValue = result;
//
//        } catch (Exception e) {
//
//            if (this.MyOwnEvent4RaiseIT != null)
//                this.MyOwnEvent4RaiseIT.onError((T) e);
//        }
//    }
//
//    public T getStickyReturnValue() {
//        //asv la muvi aqui seria tener un exechuter Manager
//        return this.task_stickyReturnValue;
//    }
//
//    @Override
//    protected void onCancelled() {
//
//        this.task_status = Enum_Tasks_STATUS.FINITA;//asv todo igual un nuevo status para el error? aunk tb los trigger me determinan el estatus
//        if (this.MyOwnEvent4RaiseIT != null)
//            this.MyOwnEvent4RaiseIT = null;
//
//    }
//
//
//}
