package mobi.huanyuan.spider.runable;

import mobi.huanyuan.spider.Spider;

/**
 * Description.
 *
 * @author Jonathan L.(xingbing.lai@gmail.com)
 * @version 1.0.0 -- Datetime: 2020/2/24 23:15
 */
public abstract class SpiderTask implements Runnable {

    private String taskName;// 任务名称
    private volatile long start = 0L; // 任务开始时间
    private Thread.State state; // 线程状态

    private Thread taskInThread; // 当前任务所处的线程

    public SpiderTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        // 将当前任务与当前线程相关连，为的是后续设置中断状态做准备
        this.setTaskInThread(Thread.currentThread());
        // 记录任务执行开始时间
        start = System.currentTimeMillis();
        long num = 0;
        try {
            exe();

            // 任务执行结束，在存储任务容器中删除该任务
            if (Spider.tasks.get(this.getTaskName()) != null) {
                Spider.tasks.remove(this.getTaskName());
                if (!Thread.currentThread().isInterrupted()) {
                    System.out.println(taskName + "==>正常结束，模拟清除 耗时：" + (num / 1000) + "  实际耗时=="
                            + ((System.currentTimeMillis() - start) / 1000) + "  start=" + start + "  end=" + System.currentTimeMillis());
                } else {
                    System.out.println(taskName + "==>中断结束，模拟清除 耗时：" + (num / 1000) + "  实际耗时=="
                            + ((System.currentTimeMillis() - start) / 1000) + "  start=" + start + "  end=" + System.currentTimeMillis());
                }
            }
        } catch (Exception e) {
            long end = System.currentTimeMillis();
            System.out.println(taskName + "===>被中断了   模拟耗时：" + (num / 1000) + "  start：" + start + "  end：" + end
                    + " 实际耗时==" + ((System.currentTimeMillis() - start) / 1000));

            // 任务中断，在存储任务容器中删除该任务
            if (Spider.tasks.get(this.getTaskName()) != null) {
                Spider.tasks.remove(this.getTaskName());
            }
        }

    }

    public abstract void exe();

    // 对外提供设置任务中断的方法
    public void setInterrupted(Thread thread) {
        try {
            thread.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getter Setter方法
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Thread.State getThreadStatus() {
        this.state = Thread.currentThread().getState();
        return this.state;
    }

    public long getStart() {
        return start;
    }

    public Thread getTaskInThread() {
        return taskInThread;
    }

    public void setTaskInThread(Thread taskInThread) {
        this.taskInThread = taskInThread;
    }
}
