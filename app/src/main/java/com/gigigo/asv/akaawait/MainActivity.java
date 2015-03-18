package com.gigigo.asv.akaawait;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodSession;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


      //  BackgroundExecutorSample();
        //region MOVIDA QUE DEBE ACCEDER AL HILO UI NO CAMBIA
        dialog = ProgressDialog.show(this, "", "LoadingDialog ASYNC EXECUTOR", true);
        dialog.show();
        //endregion

        //Region AsyncExecutor
        new AsyncExecutor<ActionASV>().Execute(new ActionASV(15)).MyOwnEvent4RaiseIT= (new Event4Raise() {
            @Override
            public void onEvent(Object ta) {
                dialog.dismiss();
                dialog = null;
                Toast.makeText(getApplicationContext(),"onEvent",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFail(Object ta) {
                Toast.makeText(getApplicationContext(), "onFail", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Object ta) {
                Toast.makeText(getApplicationContext(),"onError",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPreXeChute(Object tas) {
                Toast.makeText(getApplicationContext(),"onPreXeChute",Toast.LENGTH_LONG).show();
            }
        });
        //endregion

        //AsyncExecutorSample();

    }

    private void AsyncExecutorSample() {




    }

    private void BackgroundExecutorSample() {
/*
        //region MOVIDA QUE DEBE ACCEDER AL HILO UI
        dialog = ProgressDialog.show(this, "", "LoadingDialog", true);
        dialog.show();
        //endregion

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

    //region mierdacaajena
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //endregion
}
