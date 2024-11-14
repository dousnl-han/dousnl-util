import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import com.dousnl.vo.sparrow.page1.FrontendVO;
import com.dousnl.vo.sparrow.page1.FunctionChildrenVO;
import com.dousnl.vo.sparrow.page1.FunctionVO;
import com.dousnl.vo.sparrow.page1.SparrowVO;
import com.dousnl.vo.sparrow.page1.TableVO;
import com.google.common.collect.Lists;

import java.io.*;
import java.util.List;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/10/30
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/10/30       hll    新增              1001
 ********************************************************************/
public class FileParserUtil {


    public static void main(String[] args) {

        BufferedReader fileReader = new BufferedReader(FileUtil.getUtf8Reader("D:\\表文档.txt"));
        StringBuffer sb = new StringBuffer();
        boolean filed = false;
        List<TableVO> tableVOS = Lists.newArrayList();
        FunctionVO functionVO = new FunctionVO();
        functionVO.setChildren(Lists.newArrayList());
        while (true) {
            try {
                String line = fileReader.readLine();
                if (line == null) {
                    break;
                }
                if (line.contains("：")) {
                    final String[] split = line.trim().split("：");
                    if (split.length == 2) {
                        TableVO tableVO = new TableVO();
                        String tableField = split[1];
                        tableVO.setTableFieldDesc(split[0]);
                        tableVO.setTableField(tableField);
                        tableVO.setTableFieldAlias(tableField);
                        if (tableField.contains("_")) {
                            final String[] tableFieldSplit = tableField.split("_");
                            StringBuffer tableFieldSplitBuffer = new StringBuffer();
                            int i = 1;
                            for (String s : tableFieldSplit) {
                                if (i == 1) {
                                    tableFieldSplitBuffer.append(s.toLowerCase());
                                }
                                if (i > 1) {
                                    tableFieldSplitBuffer.append(s.substring(0, 1).toUpperCase() + s.substring(1));
                                }
                                i++;
                            }
                            tableVO.setTableFieldAlias(tableFieldSplitBuffer.toString());
                        }
                        tableVOS.add(tableVO);
                    }
                }
                if (line.contains("功能")) {
                    filed = true;
                    continue;
                }
                if (filed) {
                    String[] split = line.trim().split(":");
                    FunctionChildrenVO functionChildrenVO = new FunctionChildrenVO();
                    functionChildrenVO.setType(Integer.valueOf(split[0]));
                    functionChildrenVO.setEndpointName(split[1]);
                    functionChildrenVO.setPath(split[2]);
                    functionVO.getChildren().add(functionChildrenVO);
                }
                sb.append(line);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        for (FunctionChildrenVO functionChildrenVO : functionVO.getChildren()) {

        }
        System.out.println(JSON.toJSONString(tableVOS));
        System.out.println(JSON.toJSONString(functionVO));

        SparrowVO sparrowVO = new SparrowVO();
        sparrowVO.setTemplateCode("ct_f360509bfa6b4f6b89569a98912f4dce");
        sparrowVO.setFrontend(new FrontendVO());
        sparrowVO.setBackend(Lists.newArrayList());

    }
}
