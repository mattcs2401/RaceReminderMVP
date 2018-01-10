package mcssoft.com.raceremindermvp.interfaces;

import mcssoft.com.raceremindermvp.model.impl.MainModelImpl;

public interface IModelTask {

    void onPostExecute(Object result, MainModelImpl.OpType opType);
}
