/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pae.umy.projectktp2.coba;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Asus
 */
@Controller
public class DummyController {
    
    DummyJpaController dummyController = new DummyJpaController();
    List<Dummy> data = new ArrayList<>();
    
    
    @RequestMapping("/read")
    @ResponseBody
    public List<Dummy> getDummy(){
    try{
        data = dummyController.findDummyEntities();
    }
    catch(Exception e){}
    
    return data;
    }
    
     @PostMapping(value="/newdata", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
     @ResponseBody
       public String newDummy(@RequestParam("gambar") MultipartFile file, HttpServletRequest req) throws ParseException, Exception{
        Dummy dumdata = new Dummy();


        String tgl = req.getParameter("tanggal");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tgl);
        byte[] img = file.getBytes();
        dumdata.setTanggal(date);
        dumdata.setGambar(img);
        
        dummyController.create(dumdata);
        return "created";
    }
        
       
       @RequestMapping(value="/create", method = RequestMethod.GET ,produces = {
           MediaType.IMAGE_JPEG_VALUE})
       public ResponseEntity<byte[]> getImg(@RequestParam( value = "id", required = true)int id, Model model) throws Exception{
           Dummy d = dummyController.findDummy(id);
           byte[] img = d.getGambar();
           return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(img);
       }
}
