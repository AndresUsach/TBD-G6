package edu.usach.tbdgrupo5.rest;

import edu.usach.tbdgrupo5.Time;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8085")
@RestController
@RequestMapping("/time")
public class TimeService
{
    private Time time;

    @RequestMapping( method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getTime()
    {
        this.time = Time.getInstance();
        Map<String, Object> result = mapSingle("time", this.time.getCurrentDateTime());
        return result;
    }

    @RequestMapping(value = "/artistas", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getTimeArtistas()
    {
        this.time = Time.getInstance();
        Map<String, Object> result = mapSingle("time", this.time.getArtistas());
        return result;
    }

    @RequestMapping(value = "/generos", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getTimeGeneros()
    {
        this.time = Time.getInstance();
        Map<String, Object> result = mapSingle("time", this.time.getGeneros());
        return result;
    }

    @RequestMapping(value = "/grafo", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getTimeGrafo()
    {
        this.time = Time.getInstance();
        Map<String, Object> result = mapSingle("time", this.time.getGrafo());
        return result;
    }

    @RequestMapping(value = "/mapa", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getTimeMapa()
    {
        this.time = Time.getInstance();
        Map<String, Object> result = mapSingle("time", this.time.getMapa());
        return result;
    }

    @RequestMapping(value = "/date", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCurrentDate()
    {
        this.time = Time.getInstance();
        Map<String, Object> result = mapSingle("date", this.time.getCurrentDate());
        return result;
    }

    private Map<String, Object> mapSingle(String key1, Object value1) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put(key1, value1);
        return result;
    }
}
