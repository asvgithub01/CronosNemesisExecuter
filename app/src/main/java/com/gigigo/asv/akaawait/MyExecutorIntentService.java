package com.gigigo.asv.akaawait;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyExecutorIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.gigigo.asv.akaawait.action.FOO";
    private static final String ACTION_BAZ = "com.gigigo.asv.akaawait.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.gigigo.asv.akaawait.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.gigigo.asv.akaawait.extra.PARAM2";

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyExecutorIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyExecutorIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public MyExecutorIntentService() {
        super("MyExecutorIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            DoServiceStuff(intent);


//            final String action = intent.getAction();
//            if (ACTION_FOO.equals(action)) {
//                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
//                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
//                handleActionFoo(param1, param2);
//            } else if (ACTION_BAZ.equals(action)) {
//                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
//                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
//                handleActionBaz(param1, param2);
//            }
        }
    }

    private void DoServiceStuff(Intent intent) {

        String Last_Task_Name = intent.getStringExtra("Last_Task_Name");//todo constants

        AsyncTaskObjectModel myTask = AsyncTaskManagerExecutor.getTaskIfExistInCollection(Last_Task_Name);


        if (myTask != null) {
            if (myTask.task_status == Enum_Tasks_STATUS.FINITA) {
                myTask.MyOwnEvent4RaiseIT.onEvent(myTask.task_stickyReturnValue);
                AsyncTaskManagerExecutor.RemoveTaskFromCollection(myTask);//asv unavez devuelto el tema lo eliminamos de la coleccion
            } else {
                if (myTask.task_status == Enum_Tasks_STATUS.NOT_RUN_YET ||
                        myTask.task_status == Enum_Tasks_STATUS.PENDIENTE)
                    myTask.execute();
                if (myTask.task_status == Enum_Tasks_STATUS.RUNNING_FREE_YEAH)
                    Log.e("#Estamostrabajandoeneio", Last_Task_Name);
            }
        }
        //AsyncTaskManagerExecutor.AllTasks
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");


    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
