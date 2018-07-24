package com.zym.Controller;


import com.zym.entity.Persion;
import com.zym.service.PersionService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "persion")
public class PersionController {

    @Autowired
    private PersionService persionService;

    //添加(成功)
    @ResponseBody
    @RequestMapping(value = "addPersion")
    public String addPersion(Persion persion){
        System.out.println("persion="+persion);
        persionService.add(persion);
        return "添加成功";

    }
    //删除(成功)
    @ResponseBody
    @RequestMapping(value = "deletaPersion")
    public String deletaPersion(Long id){
        persionService.delete(id);
        return "删除成功";
    }

    //修改
    @ResponseBody
    @RequestMapping(value = "updatePersion")
    public String update(Persion persion){
        System.out.println(persion);
        persionService.modify(persion);
        return "修改成功";
    }

    //查单个(成功)
    @ResponseBody
    @RequestMapping(value = "selectOne")
    public Persion selectPersion(Long id){
        Persion one = persionService.selectOne(id);
        return one;
    }
    //查所有(成功)
    @RequestMapping(value = "selectAll")
    @ResponseBody
    public List<Persion> selectAll(){
        List<Persion> all = persionService.selectAll();
        return all;
    }

    /**
     * 分页查(成功)
     * new PageRequest(pageone, pagetwo) pageone表示第几页，下标从0开始，pagetwo表示展示多少条数据
     */
    @ResponseBody
    @RequestMapping(value = "selectByPage" )
    public List<Persion> selectByPage(int pageone,int pagetwo){
        List<Persion> persions = persionService.selectPage(pageone, pagetwo);
        return persions;
    }

    //模糊查询
    @RequestMapping("selectLike")
    @ResponseBody
    public List<Persion> selectLike(String name){

        List<Persion> persions = persionService.selectLike("%"+name+"%");
        return persions;
    }

    @ResponseBody
    @RequestMapping(value = "/getPersion")
    public List<Persion> getUser(int pageNum,int pageSize,Persion persion) {

        List<Persion> persionList = persionService.findAll(pageNum, pageSize, persion);
        return persionList;
    }
}
