package ab.branch;

import java.util.concurrent.RecursiveTask;

/**
 * @author 刘晨
 * @create 2017-11-20 22:37
 * To change this template use File | Settings | Editor | File and Code Templates.
 * 使用RecursiveTask来进行分支合并
 **/
public class RecursiveTest extends RecursiveTask<Long>{

    /**
     * 该方法定义了将任务拆分成子任务的逻辑，以及无法再拆分货不方便再拆分时，生成单个子任务结果的逻辑
     * @return
     */
    @Override
    protected Long compute() {
        return null;
    }
}
