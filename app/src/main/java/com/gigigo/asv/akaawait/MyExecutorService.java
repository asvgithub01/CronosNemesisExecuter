package com.gigigo.asv.akaawait;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Actualmente no usamos service a secas, ya que el ciclo de vida del intentservice coincide con lo que necesitamos
 * aunk que es mejor tener algo siempre en memoria, q no necesitamos 24/7 pero que cuando lo necesitamos lo tenemos ya instanciado
 * o
 * instanciar lo que necesitamos, hacer lo q sea y eliminarlo
 *
 * ????
 *
 * A mi me gusta más el 2º caso ya que así controlas mas que hay o no en memoria y tb q el android SO
 * no haga el mal y por necesidad te borre el servicio que pensaba as q estaba alive
 */
public class MyExecutorService extends Service {
    public MyExecutorService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        if (intent != null) ManageTasks(intent);
        return null;
    }

    private void ManageTasks(Intent intent) {

        String Last_Task_Name = intent.getStringExtra("Last_Task_Name");//todo constants

        if (!Last_Task_Name.equals("")) {
            AsyncTaskObjectModel myTask = AsyncTaskManagerExecutor.getTaskIfExistInCollection(Last_Task_Name);
            //en resumen si la tarea finalizo devolvemos su valor y eliminamos la tarea, si no se ejecuto la iniciamos y si esta corriendo, pues logeamos q esta trabajando en ello(con lo cual interrunpimos la ejecucion en el caso de volver a llamarla
            if (myTask != null) {
                if (myTask.task_status == Enum_Tasks_STATUS.FINITA) {
                    myTask.MyOwnEvent4RaiseIT.onEvent(myTask.task_stickyReturnValue);
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
