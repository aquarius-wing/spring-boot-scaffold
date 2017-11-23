package generator;

import org.junit.Test;

public class GenerateMain {

    private SpeedGenerator speedGenerator = new SpeedGenerator();

    /**
     * 新增表推荐使用：生成所有表对应的实体类,dao,mapper文件，但是不刷新已生成文件。
     */
    @Test
    public void generateAllWithNoFlush() throws Exception {
        speedGenerator.generateAllWithNoFlush();
    }

    /**
     * 刷新表推荐使用：生成所有表对应的实体类,dao,mapper文件，且刷新已生成文件
     */
    @Test
    public void generateAllWithFlush() throws Exception {
        speedGenerator.generateAllWithFlush();
    }

}
