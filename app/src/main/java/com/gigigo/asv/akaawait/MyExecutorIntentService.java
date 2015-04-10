package com.gigigo.asv.akaawait;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 */
public class MyExecutorIntentService extends IntentService {

    public MyExecutorIntentService() {
        super("MyExecutorIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            ManageTasks(intent);
        }
    }

    private void ManageTasks(Intent intent) {

        String Last_Task_Name = intent.getStringExtra("Last_Task_Name");//todo constants
        boolean Is_Task_Sticky = intent.getBooleanExtra("Is_Task_Sticky", false);//todo constants   Is_Task_Sticky

        if (!Last_Task_Name.equals("")) {
            AsyncTaskObjectModel myTask = AsyncTaskManagerExecutor.getTaskIfExistInCollection(Last_Task_Name);
            //en resumen si la tarea finalizo devolvemos su valor y eliminamos la tarea, si no se ejecuto la iniciamos y si esta corriendo, pues logeamos q esta trabajando en ello(con lo cual interrunpimos la ejecucion en el caso de volver a llamarla
            if (myTask != null) {
                Log.e(myTask.task_status.toString(), Last_Task_Name);

                if (myTask.task_status == Enum_Tasks_STATUS.FINITA) {
                    myTask.MyOwnEvent4RaiseIT.onEvent(myTask.task_stickyReturnValue);

                    if (!Is_Task_Sticky)
                        AsyncTaskManagerExecutor.RemoveTaskFromCollection(myTask);//asv una vez devuelto el tema lo eliminamos de la coleccion

                } else {
                    if (myTask.task_status == Enum_Tasks_STATUS.NOT_RUN_YET || myTask.task_status == Enum_Tasks_STATUS.PENDIENTE)
                        myTask.execute();


                    if (myTask.task_status == Enum_Tasks_STATUS.RUNNING_FREE_YEAH)
                        Log.e("#Estamostrabajandoeneio", Last_Task_Name);
                }
            }
        }
        //el return de la tarea, callback o como se quiera llamar  se hace a traves del raise de los eventos
        //mandar mensajes desde el service según tengo entendido es una fuente de putadas ^_^
    }

}
