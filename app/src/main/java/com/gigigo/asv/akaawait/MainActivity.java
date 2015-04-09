package com.gigigo.asv.akaawait;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.inputmethod.InputMethodSession;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    ProgressDialog dialog;

    AsyncTaskManagerExecutor myManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region MOVIDA QUE DEBE ACCEDER AL HILO UI NO CAMBIA
        dialog = ProgressDialog.show(this, "", "LoadingDialog ASYNC EXECUTOR", true);
        dialog.show();
        //endregion

        //region Llamadas al resto de Implementaciones del Asyncroneo fino ;)
        //BackgroundExecutorSample();
        //LastAsyncExecutorSample();
        //endregion

        //region Ejecucion de un proceso asincrono + programación secuencial con Manager + Service +Sticky Values
        myManager = new AsyncTaskManagerExecutor(this.getApplicationContext());//asv todo esto no deberia ser asine, el manager deberia ser satic, xo necesita el contxt pa llamar al servicio
        myManager.Execute((Action) (new ActionASV(15))).MyOwnEvent4RaiseIT = (new Event4Raise() {
            @Override
            public void onEvent(Object ta) {
                dialog.dismiss();
                dialog = null;
                Toast.makeText(getApplicationContext(), "onEvent", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFail(Object ta) {
                Toast.makeText(getApplicationContext(), "onFail", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Object ta) {
                Toast.makeText(getApplicationContext(), "onError", Toast.LENGTH_LONG).show();
            }

        });
        //endregion

    }

    /**
     * Esta utiliza la sintaxis mas comprimida posible y q será la forma más normal de hacer las llamadas de este tipo
     */
    private void LastAsyncExecutorSample() {
        //region AsyncExecutor
        new AsyncExecutor<ActionASV>().Execute((new ActionASV(15))).MyOwnEvent4RaiseIT = (new Event4Raise() {
            @Override
            public void onEvent(Object ta) {
                dialog.dismiss();
                dialog = null;
                Toast.makeText(getApplicationContext(), "onEvent", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFail(Object ta) {
                Toast.makeText(getApplicationContext(), "onFail", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Object ta) {
                Toast.makeText(getApplicationContext(), "onError", Toast.LENGTH_LONG).show();
            }


        });
        //endregion
    }



    /**
     * Este lo hace de la manera mas explicita posible
     */
    private void BackgroundExecutorSample() {



        //region crear el Ejecutador en 2º plano
        BackgroundExecutor T1000 = new BackgroundExecutor();
        //endregion

        //region CODIGO QUE ACCEDE AL HILO UI, pero debe realizarse despues de finalizar el proceso async
        T1000.onFinish = new InputMethodSession.EventCallback() {
            @Override
            public void finishedEvent(int seq, boolean handled) {
                dialog.dismiss();
                dialog = null;
            }
        };
        //endregion

        //region Crear el command que quereos q corra en otro hilo
        ActionASV actionASV = new ActionASV(20);
        //endregion

        //region lanzamos la ejecucion de la tarea
        T1000.Execute(actionASV);

        //endregion

        /*Misma llamada en otro formato*/
        //region MOVIDA QUE DEBE ACCEDER AL HILO UI NO CAMBIA
        dialog = ProgressDialog.show(this, "", "LoadingDialog", true);
        dialog.show();
        //endregion

        //region version reformateada de lo mismo
        new BackgroundExecutor().Execute(new ActionASV(15)).onFinish = (new InputMethodSession.EventCallback() {
            @Override
            public void finishedEvent(int seq, boolean handled) {
                dialog.dismiss();
                dialog = null;
            }
        });
//endregion
    }


}
