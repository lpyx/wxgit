package com.l.pyx;

import java.util.HashMap;

public class SopTransfer {
    private static String url_dev = "http://sopdef-dev.test.blacklake.tech";
    private static String url_local = "http://localhost:8080";
    private static String url_feature = "http://sopdef-feature.test.blacklake.tech";
    private static String url_test = "http://sopdef-test.test.blacklake.tech";
    private static String url_staging = "http://sopdef-staging.prod.blacklake.tech";
    private static String url_prod = "http://sopdef.prod.blacklake.tech";

    private static HashMap<String, String> envUrlMap = new HashMap<String, String>();

    static {
        envUrlMap.put("dev", url_dev);
        envUrlMap.put("local", url_local);
        envUrlMap.put("feature", url_feature);
        envUrlMap.put("test", url_test);
        envUrlMap.put("staging", url_staging);
        envUrlMap.put("prod", url_prod);
    }

    private static String url_import_sop = "/v1/_import/_import_sop";
    private static String url_import_sop_template = "/v1/_import/_import_sop_template";
    private static String url_export_sop = "/v1/_export/_export_sop?sopId={sopId}";
    private static String url_export_sop_template = "/v1/_export/_export_sop_template?templateId={templateId}";

    public static void transferSopTemplate(String fromEnv, Long fromOrgId, Long fromSopTemplateId,
                                   String toEnv, Long toOrgId) {


        String exportContent = null;
        try {
            println("开始导出");
            String exportUrl = formatExportSopTemplateUrl(fromEnv, fromSopTemplateId);
            exportContent = HttpUtils.httpGetSend(fromOrgId, exportUrl, new HashMap<String,String>());
            if (exportContent == null || exportContent.isEmpty()) {
                println("导出失败");
                return;
            }
            println("导出成功");
        } catch (Exception ex) {
            println("导出失败:" + ex.getMessage());
            return;
        }
        try {
            println("开始导入");
            String importUrl = formatImportSopTemplateUrl(toEnv);
            String importResult = HttpUtils.httpPostSend(toOrgId, importUrl, exportContent,new HashMap<String,String>());
            println("导入成功:" + importResult);
        } catch (Exception ex) {
            println("导入失败:" + ex.getMessage());
            ex.printStackTrace();
        }
    }


    public static void transferSop(String fromEnv, Long fromOrgId, Long fromSopId,
                                   String toEnv, Long toOrgId) {


        String exportContent = null;
        try {
            println("开始导出");
            String exportUrl = formatExportSopUrl(fromEnv, fromSopId);
            exportContent = HttpUtils.httpGetSend(fromOrgId, exportUrl,new HashMap<String,String>());
            if (exportContent == null || exportContent.isEmpty()) {
                println("导出失败");
                return;
            }
            println("导出成功");
        } catch (Exception ex) {
            println("导出失败:" + ex.getMessage());
            return;
        }
        try {
            println("开始导入");
            String importUrl = formatImportSopUrl(toEnv);
            String importResult = HttpUtils.httpPostSend(toOrgId, importUrl, exportContent,new HashMap<String,String>());
            println("导入结果:" + importResult);
        } catch (Exception ex) {
            println("导入失败:" + ex.getMessage());
            ex.printStackTrace();
        }
    }


    private static String formatImportSopTemplateUrl(String env) {
        StringBuffer sb = new StringBuffer();
        if (envUrlMap.containsKey(env)) {

        } else {
            println("环境" + env + "不合法");
        }
        sb.append(envUrlMap.get(env));
        sb.append(url_import_sop_template);
        return sb.toString();
    }

    private static String formatImportSopUrl(String env) {
        StringBuffer sb = new StringBuffer();
        if (envUrlMap.containsKey(env)) {

        } else {
            println("环境" + env + "不合法");
        }
        sb.append(envUrlMap.get(env));
        sb.append(url_import_sop);
        return sb.toString();
    }

    private static String formatExportSopTemplateUrl(String env, Long fromSopTemplateId) {
        StringBuffer sb = new StringBuffer();
        if (envUrlMap.containsKey(env)) {

        } else {
            println("环境" + env + "不合法");
        }
        sb.append(envUrlMap.get(env));
        sb.append(url_export_sop_template.replace("{templateId}", fromSopTemplateId.toString()));
        return sb.toString();
    }

    private static String formatExportSopUrl(String env, Long fromSopId) {
        StringBuffer sb = new StringBuffer();
        if (envUrlMap.containsKey(env)) {

        } else {
            println("环境" + env + "不合法");
        }
        sb.append(envUrlMap.get(env));
        sb.append(url_export_sop.replace("{sopId}", fromSopId.toString()));
        return sb.toString();
    }

    private static void println(Object obj) {
        System.out.println("*********" + obj.toString() + "**********");
    }

    public static void main(String[] args) {
        //8
        //18
        //21
        //16
        //2
        //11
        //14
        //24
        //5
        String fromEnv = "local";
        Long fromOrgId = 162L;

        Long fromSopTemplateId = 24L;

        //
        Long toOrgId = 186L;
        String toEnv = "feature";

        transferSopTemplate(fromEnv, fromOrgId, fromSopTemplateId, toEnv,toOrgId);
    }
}
