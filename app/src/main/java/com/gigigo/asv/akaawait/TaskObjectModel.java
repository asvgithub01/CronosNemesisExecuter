package com.gigigo.asv.akaawait;

/**
 * Created by Alberto on 07/04/2015.
 *
 * Se puede usar para encapsular las caractericas que queremos en nuestro objeto asincrono, ya que en AsyncTaskObjectModel se han metido directamente
 * pero si creamos el SchedulerJobObjectModel tendriamos que volver a añadirle los campos...
 * evolution?adaptation?
 * no
 * symbiosis!
 */
public abstract class TaskObjectModel {
    public String Task_Key;
    public Action Task4Execute;
    public Enum<Enum_Tasks_STATUS> Task_Status;
    public Object Task_Sticky_ReturnValue; //object o WTF???
}
