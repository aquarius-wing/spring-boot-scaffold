package generator;

import cn.org.rapid_framework.generator.GeneratorFacade;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class SpeedGenerator {

    private String corePath = System.getProperty("user.dir");
    private String templateRootDir = corePath + "/src/test/resources/template";
    private String basePackagePath = "/src/main/java/com/example/demo";

    private String rootPath;
    private String modelPath;
    private String queryPath;
    private String mapPath;
    private String mapCommonPath;
    private String daoPath;
    private String daoBasePath;

    {
        rootPath = corePath;
        modelPath = rootPath + basePackagePath + "/model";
        queryPath = rootPath + basePackagePath + "/query";
        mapPath = rootPath + "/src/main/resources/mapper";
        mapCommonPath = mapPath + "/common";
        daoPath = rootPath + basePackagePath + "/dao";
        daoBasePath = daoPath + "/base";
    }

    //生成所有表对应的实体类,dao,mapper文件，但是不刷新已存在文件
    void generateAllWithNoFlush() throws Exception {
        Boolean result = setLocalPath();
        if (result) {
            GeneratorFacade g = new GeneratorFacade();
            g.getGenerator().setTemplateRootDir(templateRootDir);
            g.generateByAllTable();
        } else {
            System.out.println("generate error");
        }
    }

    //生成所有表对应的实体类,dao,mapper文件，但是不刷新已存在文件
    void generateAllWithFlush() throws Exception {
        deleteDirectoryFiles(modelPath);
        deleteDirectoryFiles(queryPath);
        deleteDirectoryFiles(mapCommonPath);
        deleteDirectoryFiles(daoBasePath);
//        deleteDirectoryFiles(mapPath);
//        deleteDirectoryFiles(daoPath);
        generateAllWithNoFlush();
    }


    //设置输出路径为本项目路径
    private Boolean setLocalPath() {
        InputStream in = SpeedGenerator.class.getResourceAsStream("../generator.properties");
        FileOutputStream outputStream = null;
        try {
            Properties props = new Properties();
            props.load(in);
            props.setProperty("outRoot", rootPath);
            outputStream = new FileOutputStream(SpeedGenerator.class.getResource("../generator.properties").getFile());
            props.store(outputStream, "comment");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void deleteDirectoryFiles(String path) {
        File directory = new File(path);
        if (!directory.exists() || !directory.isDirectory() || directory.listFiles() == null) {
            return;
        }
        for (File file : directory.listFiles()) {
            if (file.delete()) {
                System.out.println(file.getAbsolutePath() + " is deleted");
            }
        }
    }
}
