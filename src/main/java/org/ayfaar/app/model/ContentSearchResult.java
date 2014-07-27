package org.ayfaar.app.model;

import lombok.Data;


@Data
// fixme: в пакете модель храняться классы которые связаны с базой данных, лучше его отсюда перенести
public class ContentSearchResult {
    private String uri;
    private String name;
    private String quote;
}
