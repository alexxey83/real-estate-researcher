package com.gear.testSpringMVC.Entities;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public enum SubwayStations {
	
	// M1 - Red branch
	PROSPEKT_VETERANOV("Проспект Ветеранов", 27, false),
	LENINSKIY_PROSPEKT("Ленинский Проспект", 25, true),
	AVTOVO("Автово", 23, false),
	KIROVSKIY_ZAVOD("Кировский Завод", 22, false),
	NARVSKAYA("Нарвская", 19, true),
	BALTIYSKAYA("Балтийская", 18, true),
	TEKHNOLOGICHESKIY_INSTITUT("Технологический Институт", 17, true),
	PUSHKINSKAYA("Пушкинская", 15, true),
	VLADIMIRSKAYA("Владимирская", 10, true),
	PLOSCHAD_VOSSTANIA("Площадь Восстания", 14, true),
	CHERNYSHEVSKAYA("Чернышевская", 16, true),
	PLOSCHAD_LENINA("Площадь Ленина", 17, true),
	VYBORGSKAYA("Выборгская", 19),
	LESNAYA("Лесная", 21),
	PLOSCHAD_MUZHESTVA("Площадь Мужества", 23),
	POLITEKHNICHESKAYA("Политехническая", 24),
	AKADEMICHESKAYA("Академическая", 26),
	GRAZHDANSKIY_PROSPEKT("Гражданский Проспект", 29),
	DEVYATKINO("Девяткино", 31),
	
	// M2 - Blue branch
	KUPCHINO("Купчино", 29),
	ZVYOZDNAYA("Звездная", 27),
	MOSKOVSKAYA("Московская", 24, true),
	PARK_POBEDY("Парк победы", 22, true),
	ELEKRTOSILA("Электросила", 21, true),
	MOSKOVSKIYE_VOROTA("Московские ворота", 19, true),
	FRUNZENSKAYA("Фрунзенская", 18, true),
	SENNAYA_PLOSCHAD("Сенная площадь", 10, true),
	NEVSKIY_PROSPEKT("Невский проспект", 16, true),
	GORKOVSKAYA("Горьковская", 19, true),
	PETROGRADSKAYA("Петроградская", 20, true),
	CHYORNAYA_RECHKA("Черная речка", 22),
	PIONERSKAYA("Пионерская", 24),
	UDELNAYA("Удельная", 26),
	OZERKI("Озерки", 29),
	PROSPEKT_PROSVESCHENIYA("Проспект просвещения", 31),
	PARNAS("Парнас", 33),
	
	// M3 - Green branch
	PRIMORSKAYA("Приморская", 20, true),
	VASILEOSTROVSKAYA("Василеостровская", 17, true),
	GOSTINY_DVOR("Гостиный двор", 14, true),
	MAYAKOVSKAYA("Маяковская", 12, true),
	ELIZAROVSKAYA("Елизаровская", 15, false),
	LOMONOSOVSKAYA("Ломоносовская", 17, true),
	PROLETARSKAYA("Пролетарская", 19),
	OBUHOVO("Обухово", 21),
	RYBATSKOE("Рыбацкое", 24),
	
	// M4 - Yellow branch
	SPASSKAYA("Спасская", 10, true),
	DOSTOEVSKAYA("Достоевская", 8, true),
	LIGOVSKIY_PROSPEKT("Лиговский проспект", 7, true),
	PLOSCHAD_ALEKSANDRA_NEVSKOGO("Площадь Александра Невского", 5, true),
	NOVOCHERKASSKAYA("Новочеркасская", 0, true),
	LADOZHSKAYA("Ладожская", 5, true),
	PROSPEKT_BOLSHEVIKOV(new String[] {"Проспект большевиков", "Большевиков проспект"}, 7),
	ULITSA_DYBENKO("Улица дыбенко", 9),
	
	//M5 - Violet branch
	MEZHDUNARODNAYA("Международная", 23, true),
	BUKHARESTSKAYA("Бухарестская", 22, true),
	VOLKOVSKAYA("Волковская", 20, true),
	OBVODNY_KANAL("Обводный канал", 18, true),
	ZVENIGORODSKAYA("Звенигородская", 16, true),
	SADOVAYA("Садовая", 11, true),
	ADMIRALTEYSKAYA("Адмиралтейская", 16, true),
	SPORTIVNAYA("Спортивная", 19, true),
	CHKALOVSKAYA("Чкаловская", 20),
	KRESTOVSKIY_OSTROV("Крестовский остров", 22, true),
	STARAYA_DEREVNYA("Старая деревня", 24),
	KOMENDANTSKIY_PROSPEKT("Комендантский проспект", 27);
	
	private List<String> names;
	
	public int TimeToWork = 0;
	public String Name;
	public boolean isPrefer = false;
	
	public static SubwayStations valueByName(String name) {
		if (name == null) {
			throw new NullPointerException("name is null");
		}
		
		SubwayStations[] ss = values();
		for (int i = 0; i < ss.length; i++) {
			SubwayStations ssNext = ss[i];
			if (StringUtils.equalsIgnoreCase(ssNext.Name, name)) {
				return ssNext;
			}
			
			if (ssNext.names != null) {
				for (String nextName : ssNext.names) {
					if (StringUtils.equalsIgnoreCase(nextName, name)) {
						return ssNext;
					}
				}
			}
		}
		
		throw new IllegalArgumentException("No enum constant " + SubwayStations.class.getCanonicalName() + ": " + name);
	}
	
	private SubwayStations(String name, int timeToWork) {
		this.Name = name;
		this.TimeToWork = timeToWork;
	}
	
	private SubwayStations(String[] names, int timeToWork) {
		this.Name = names[0];
		this.names = Arrays.asList(names);
		this.TimeToWork = timeToWork;
	}
	
	private SubwayStations(String name, int timeToWork, boolean isPrefer) {
		this.Name = name;
		this.TimeToWork = timeToWork;
		this.isPrefer = isPrefer;
	}
	
	private SubwayStations(String[] names, int timeToWork, boolean isPrefer) {
		this.Name = names[0];
		this.names = Arrays.asList(names);
		this.TimeToWork = timeToWork;
		this.isPrefer = isPrefer;
	}
}
