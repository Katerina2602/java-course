package edu.project3.cli;

import edu.project3.file.model.RequestRecord;
import edu.project3.stat.model.CommonStat;
import edu.project3.stat.model.RequestedResourceStat;
import edu.project3.stat.model.Stat;
import edu.project3.stat.model.StatFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleRendererTest {
    private final Renderer renderer = new ConsoleRenderer();

    @Test
    void willRenderAsMarkdown() {
        List<Stat> stats = List.of(createCommonStat(), createRequestedResourcesStat());

        String expectedRender = """
            ### Общая информация

            | Метрика | Значение |
            |:-------:|:---------|
            | Файлы | file1.txt |
            | Начальная дата | 2023-09-01 |
            | Конечная дата | 2023-09-02 |
            | Количество запросов | 1 |
            | Средний размер ответа | 0.0b |
            ### Запрашиваемые ресурсы

            | Ресурс | Количество |
            |:-------:|:---------|
            | '/google' | 1 |
            | '/yandex' | 1 |
            """;

        String actualRender = renderer.render(stats, StatFormat.MARKDOWN);

        assertEquals(expectedRender, actualRender);
    }

    @Test
    void willRenderAsAdoc() {
        List<Stat> stats = List.of(createCommonStat(), createRequestedResourcesStat());

        String expectedRender = """
            === Общая информация
            [options="header"]
            |====================
            | Метрика | Значение
            | Файлы | file1.txt
            | Начальная дата | 2023-09-01
            | Конечная дата | 2023-09-02
            | Количество запросов | 1
            | Средний размер ответа | 0.0b
            |=======================
            === Запрашиваемые ресурсы
            [options="header"]
            |====================
            | Ресурс | Количество
            | \\'/google' | 1
            | \\'/yandex' | 1
            |=======================
            """;

        String actualRender = renderer.render(stats, StatFormat.ADOC);

        assertEquals(expectedRender, actualRender);
    }

    private Stat createCommonStat() {
        Stat commonStat = new CommonStat();
        commonStat.addFilename("file1.txt");
        commonStat.addStartAndEndDates(LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 2));
        commonStat.collect(new RequestRecord("1.1.1.1", OffsetDateTime.now(), "", 200, 0L, ""));

        return commonStat;
    }

    private Stat createRequestedResourcesStat() {
        Stat requestedResourceStat = new RequestedResourceStat();
        requestedResourceStat.collect(new RequestRecord("1.1.1.1", OffsetDateTime.now(), "GET /google HTTP/1", 200, 0L, ""));
        requestedResourceStat.collect(new RequestRecord("1.1.1.1", OffsetDateTime.now(), "GET /yandex HTTP/1", 200, 0L, ""));

        return requestedResourceStat;
    }
}
