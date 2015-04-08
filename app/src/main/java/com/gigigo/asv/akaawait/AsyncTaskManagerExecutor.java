package com.gigigo.asv.akaawait;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alberto on 07/04/2015.
 */
public class AsyncTaskManagerExecutor {
    static List<AsyncTaskObjectModel> AllTasks = new ArrayList<>();
    static Context mContext;
    //todo quizas deberiamos escuchar el status de las tareas para eliminarlas de la coleccion.
    //se puede ahcer en el Execute un for previo en plan recorre las tareas y las finitas(sin el sticky) te las calzas
    //o hacerlo explicitamente tras el onfinish de la tarea en la propia activity, a gusto del consumidor

    public AsyncTaskManagerExecutor(Context context) {
        mContext = context;
    }

    public static AsyncTaskObjectModel Execute(Action act) {

        //buscamos en la coleccion  getMyInvoker();, si existe no hacemos nada y llamamos al servicio(es el servicio el que dispara el onevent)
        //si no existe creamos el new AsyncTaskObjectModel y llamamos al servicio con un intent(y es el servicio el que hace el execute)

//asv mierda fuck y fock, aki el getmyInvoker no me devuelve la linea del mainactivity coomo io quiero
        //hay q pasar del 2º tb
        String Task_Key_Name = getMyInvoker();
        AsyncTaskObjectModel newAsyncTask = getTaskIfExistInCollection(Task_Key_Name);

        //Intent intent = new Intent(mContext, MyExecutorService.class);
        Intent intent = new Intent(mContext, MyExecutorIntentService.class);
        intent.putExtra("Last_Task_Name", Task_Key_Name);

        if (newAsyncTask == null) {
            newAsyncTask = new AsyncTaskObjectModel();
            newAsyncTask.task_action2Execute = act;
            newAsyncTask.task_name_key = Task_Key_Name;
            newAsyncTask.task_status = Enum_Tasks_STATUS.PENDIENTE;
            newAsyncTask.task_stickyReturnValue = null;

            AllTasks.add(newAsyncTask);
        } else {
            //solo llamar al servicio con el name xo sin añadir de nuevo la tarea a la lista
        }
        mContext.startService(intent);


        //mContext.bindService(intent,null,Context.BIND_IMPORTANT);
        return newAsyncTask;
    }


    public static AsyncTaskObjectModel getTaskIfExistInCollection(String TaskKeyName) {
        AsyncTaskObjectModel resultTask = null;
        if (AllTasks != null) {
            for (AsyncTaskObjectModel itemTask : AllTasks) {
                if (itemTask.task_name_key.equals(TaskKeyName)) {
                    resultTask = itemTask;
                    break;
                }
            }
        }
        return resultTask;
    }

    public static void RemoveTaskFromCollection(String TaskKeyName) {
        RemoveTaskFromCollection(getTaskIfExistInCollection(TaskKeyName));
    }

    public static void RemoveTaskFromCollection(AsyncTaskObjectModel Task4Remove) {
        AllTasks.remove(Task4Remove);
    }

    public static String getMyInvoker() {
        String invokerGüeno = "NiFUCKINGIdeaMan";
        boolean isTheNextOneTheFuckingInvoker = false;
        // String whoAmI = this.getClass().name;

        String thisClassName = "com.gigigo.asv.akaawait.AsyncTaskManagerExecutor";//asv esta fuckin mierda es al ser statico//this.getClass().getName(); //asv esto es el namespace+.nombreclase q la contiene
        Thread thread = Thread.currentThread(); //asv hay mas hilos q delitos de corrupcion tie el pp...
        //  Map<Thread, StackTraceElement[]> allTraces = Thread.getAllStackTraces();//todo asv  muy bonito, muy bonito, chorromil hilos...problema para el asv del futuro

        StackTraceElement[] tracesArray = thread.getStackTrace();

        //asv atiende, recorremos el array hasta encotrar la primera coincidencia con esta clase(provocada por la llamada al propio getMyInvoker();
        // y el siguiente item del array de la traza es nuestro invoker
        for (StackTraceElement stackTrace : tracesArray) {
            if (isTheNextOneTheFuckingInvoker) {
                invokerGüeno = stackTrace.toString();
                break;
            }
            if (stackTrace.getClassName().equals(thisClassName))
                isTheNextOneTheFuckingInvoker = true;
        }
        return invokerGüeno;
    }
}
