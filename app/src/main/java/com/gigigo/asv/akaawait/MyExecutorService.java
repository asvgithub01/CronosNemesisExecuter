package com.gigigo.asv.akaawait;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyExecutorService extends Service {
    public MyExecutorService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        DoServiceStuff(intent);
        return null;
    }

   private void DoServiceStuff(Intent intent)
   {

       String Last_Task_Name = intent.getStringExtra("Last_Task_Name");//todo constants

       AsyncTaskObjectModel myTask = AsyncTaskManagerExecutor.getTaskIfExistInCollection(Last_Task_Name);


       if (myTask != null) {
           if (myTask.task_status == Enum_Tasks_STATUS.FINITA) {
               myTask.MyOwnEvent4RaiseIT.onEvent(myTask.task_stickyReturnValue);
               AsyncTaskManagerExecutor.RemoveTaskFromCollection(myTask);//asv unavez devuelto el tema lo eliminamos de la coleccion
           } else {
               if (myTask.task_status == Enum_Tasks_STATUS.NOT_RUN_YET)
                   myTask.execute();
               if (myTask.task_status == Enum_Tasks_STATUS.RUNNING_FREE_YEAH ||
                       myTask.task_status == Enum_Tasks_STATUS.PENDIENTE)
                   Log.e("#Estamostrabajandoeneio", Last_Task_Name);
           }
       }
       //AsyncTaskManagerExecutor.AllTasks
       // TODO: Return the communication channel to the service.
       //throw new UnsupportedOperationException("Not yet implemented");


   }


}
