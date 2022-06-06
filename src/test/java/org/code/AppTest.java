package org.code;

import static org.junit.Assert.assertTrue;

import com.code.StartApplication;
import com.code.controller.Suanfa;
import com.code.entity.*;
import com.code.mapper.*;
import com.code.util.CommonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartApplication.class)
public class AppTest {

    @Autowired
    UserinfoMapper userinfoMapper;

    @Autowired
    ByyqinfoMapper byyqinfoMapper;

    @Autowired
    DatainfoMapper datainfoMapper;

    @Autowired
    ScoreinfoMapper scoreinfoMapper;
    @Autowired
    KcmbinfoMapper kcmbinfoMapper;


    @Test
    public void 生成课程目标() {
        List<Datainfo> datainfoList = datainfoMapper.selectByMap(null);
        for (Datainfo datainfo : datainfoList) {
            Kcmbinfo kc = new Kcmbinfo();
            kc.setWkname(datainfo.getName());
            Random random = new Random();
            Double a1 = Double.valueOf(random.nextInt(60)) / 100;
            Double a2 = Double.valueOf(random.nextInt(60)) / 100;
            Double a3 = Double.valueOf(random.nextInt(60)) / 100;
            kc.setWp1(a1 + "");
            kc.setWp2(a2 + "");
            kc.setWp3(a3 + "");

            Double y1 = Double.valueOf(random.nextInt(60)) / 100;
            Double y2 = Double.valueOf(random.nextInt(60)) / 100;
            Double y3 = Double.valueOf(random.nextInt(60)) / 100;
            kc.setWs1(y1 + "");
            kc.setWs2(y2 + "");
            kc.setWs3(y3 + "");


            kc.setWq1(Suanfa.nums3((1.0 - a1 - y1)) + "");
            kc.setWq2(Suanfa.nums3((1.0 - a2 - y2)) + "");
            kc.setWq3(Suanfa.nums3((1.0 - a3 - y3)) + "");

            kcmbinfoMapper.insert(kc);

        }
    }


    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        List<Datainfo> datainfoList = datainfoMapper.selectByMap(null);
        for (Datainfo datainfo : datainfoList) {
            Byyqinfo byyqinfo = new Byyqinfo();
            byyqinfo.setMname(datainfo.getName());
            Random random = new Random();
            byyqinfo.setM1("0." + random.nextInt(4) + random.nextInt(10));
            byyqinfo.setM2("0." + random.nextInt(4) + random.nextInt(10));
            byyqinfo.setM3("0." + random.nextInt(4) + random.nextInt(10));
            for (int i = 1; i <= 12; i++) {
                byyqinfo.setMcontent("毕业要求" + i);
                byyqinfo.setMintro("无");
                byyqinfoMapper.insert(byyqinfo);
            }

        }
    }

    @Test
    public void shouldAnswerWithTrue2() {
        List<Userinfo> userinfoList = userinfoMapper.selectByMap(null);

        List<Datainfo> datainfoList = datainfoMapper.selectByMap(null);
        Random random = new Random();
        for (Datainfo datainfo : datainfoList) {
            Scoreinfo s = new Scoreinfo();
            s.setUname("张飞");
            s.setTid(datainfo.getId());
            s.setTname(datainfo.getName());
            s.setCreatetime(CommonUtils.getNowDateStr());
            int pscore = random.nextInt(21);
            int yscore = random.nextInt(21);
            int qscore = random.nextInt(61);
            s.setPscore(pscore + "");
            s.setYscore(yscore + "");
            s.setQscore(qscore + "");
            s.setScore((pscore + yscore + qscore));
            scoreinfoMapper.insert(s);
        }
    }

    @Test
    public void test123() {
        List<Userinfo> userinfoList = userinfoMapper.selectByMap(null);
        for (Userinfo userinfo : userinfoList) {
            System.out.println("userinfo = " + userinfo);
        }

    }
}
