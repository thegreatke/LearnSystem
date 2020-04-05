package com.online.controller;

import com.online.service.StudentService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
public class StudentController {
    @Autowired
    StudentService studentService;

    /**
     * 返回所有学生的报名信息。
     *
     * @return jsonArray
     */

    @RequestMapping(value = "/findAllStudents", method = RequestMethod.GET)
    public JSONArray findAllNews() {
        return studentService.findAllStudents();
    }


    /**
     * 返回所有学生的留言信息。
     *
     * @return jsonArray
     */

    @RequestMapping(value = "/findAllLiuyan", method = RequestMethod.GET)
    public JSONArray findAllLiuyan() {
        return studentService.findAllLiuyan();
    }

    /**
     * 返回所有学生的留言信息。
     *
     * @return jsonArray
     */

    @RequestMapping(value = "/addLiuyan", method = RequestMethod.POST)
    public String addLiuyan(@RequestParam String id, @RequestParam String liuyan) {

        int id2 = Integer.valueOf(id);

        return studentService.addLiuyan(id2,liuyan);
    }



    /**
     * 指定删除某一学生的报名信息
     *
     * @param id
     * @return
     */
    @Transactional
    @RequestMapping(value = "/deleteStudent", method = RequestMethod.POST)

    public String deleteStudent(@RequestParam int id) {
        return studentService.deleteStudent(id);
    }


    /**
     * 指定删除某一学生的报名信息
     *
     * @param id
     * @return
     */
    @Transactional
    @RequestMapping(value = "/deleteReply", method = RequestMethod.POST)

    public String deleteReply(@RequestParam int id) {
        return studentService.deleteReply(id);
    }






    /**
     * 首次录入某一学生的得分分数
     *
     * @param id
     * @return
     */
    @Transactional
    @RequestMapping(value = "/addScoreToStudent", method = RequestMethod.POST)
    String addScoreToStudent(@RequestParam int id, @RequestParam int score) {
        return studentService.addScoreToStudent(id, score);
    }



    /**
     * 首次录入回复
     *
     * @param id
     * @return
     */
    @Transactional
    @RequestMapping(value = "/addReply", method = RequestMethod.POST)
    String addReply(@RequestParam int id, @RequestParam String reply) {
        return studentService.addReply(id, reply);
    }



    /**
     * 修改某一学生的得分分数
     *
     * @param id
     * @return
     */

    @Transactional
    @RequestMapping(value = "/changeScoreToStudent", method = RequestMethod.POST)
    String changeScoreToStudent(@RequestParam int id, @RequestParam int score) {
        return studentService.addScoreToStudent(id, score);
    }

    /**
     * 学号和姓名同时匹配, 查询某一学生所得的分数
     *
     * @param id , name
     * @return string , 实现了容错处理。
     */
    @Transactional
    @RequestMapping(value = "/getScoreOfStudent", method = RequestMethod.GET)
    String getScoreOfStudent(@RequestParam int id, @RequestParam String name) {
        int score = studentService.getScoreOfStudent(id, name);
        String score_str;
        if (score == -1) {
            score_str = "没有查询到和学号以及姓名同时匹配的账户，请检查输入的学号和姓名是否正确.";
        } else if (score == -2) {
            score_str = "此同学的得分成绩尚未录入，请耐心等待， 日后再次查询.";
        } else score_str = Integer.toString(score);
        return score_str;
    }
}
