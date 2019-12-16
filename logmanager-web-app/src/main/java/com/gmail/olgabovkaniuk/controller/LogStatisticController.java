package com.gmail.olgabovkaniuk.controller;

import com.gmail.olgabovkaniuk.dto.LogStatsDto;
import com.gmail.olgabovkaniuk.service.FileService;
import com.gmail.olgabovkaniuk.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/log-statistic")
public class LogStatisticController {

    @Autowired
    StatisticService statisticService;

    @Autowired
    FileService fileService;

    @GetMapping
    public String logStatistic(Model model) {
        return "logstatistic";
    }

    @GetMapping("/overview")
    public String getRowByPeriodStatistic(QueryParam queryParam, Model model) {
        List<LogStatsDto> logStatsDtoList = statisticService.getStatistic(queryParam);
        model.addAttribute("from_date_filter", queryParam.getFromDate());
        model.addAttribute("to_date_filter", queryParam.getToDate());
        model.addAttribute("statistic_list", logStatsDtoList);
        return "overviewpage";
    }

    @GetMapping("/file")
    public void getFile(HttpServletResponse response, QueryParam queryParam, Model model) throws Exception {
        response.setContentType("text/plain");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "file.txt");

        PrintWriter printWriter = response.getWriter();

        List<String> filteredLogRows = fileService.getFilteredLogRows(queryParam);

        filteredLogRows.forEach(printWriter::write);

        printWriter.flush();
        printWriter.close();
    }
}
