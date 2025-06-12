package co.feip.fefu2025.data.repositories

import co.feip.fefu2025.R
import co.feip.fefu2025.domain.entities.Anime
import co.feip.fefu2025.domain.repositories.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor() : AnimeRepository  {
    private val animeList = listOf(
        Anime(1,
            "Ковбой Бибоп",
            listOf("Приключения", "Драма", "Комедия"),
            1998,
            R.drawable.cowboy_bebop,
            "",
            25,
            mapOf(1 to 10, 2 to 5, 3 to 6, 4 to 8, 5 to 9, 6 to 5, 7 to 12, 8 to 22, 9 to 29, 10 to 38),
            listOf(2, 3 , 4)

        ),

        Anime(2,
            "Берсерк",
            listOf("Приключения", "Сейнен", "Экшен"),
            1998,
            R.drawable.berserk,
            "",
            25,
            mapOf(1 to 10, 2 to 5, 3 to 6, 4 to 8, 5 to 9, 6 to 5, 7 to 12, 8 to 22, 9 to 29, 10 to 38),
            listOf(1, 3, 4, 6)
        ),

        Anime(3,
            "Монстр",
            listOf("Детектив", "Драма", "Триллер"),
            2004,
            R.drawable.monster,
            "",
            56,
            mapOf(1 to 10, 2 to 5, 3 to 6, 4 to 8, 5 to 9, 6 to 5, 7 to 12, 8 to 22, 9 to 29, 10 to 38),
            listOf(1, 2, 4, 6)
        ),

        Anime(4,
            "Хеллсинг OVA",
            listOf("Экшен", "Сейнен"),
            2006,
            R.drawable.hellsing_ova,
            "",
            10,
            mapOf(1 to 10, 2 to 5, 3 to 6, 4 to 8, 5 to 9, 6 to 5, 7 to 12, 8 to 22, 9 to 29, 10 to 38),
            listOf(1, 2, 3, 6)
        ),

        Anime(
            5,
            "Ван Пис",
            listOf("Приключения", "Сёнен", "Комедия"),
            1999,
            R.drawable.one_piece,
            "",
            1016,
            mapOf(1 to 10, 2 to 5, 3 to 6, 4 to 8, 5 to 9, 6 to 5, 7 to 12, 8 to 22, 9 to 29, 10 to 38),
            listOf(7, 8 , 10)
        ),

        Anime(
          6,
            "Пираты \" Чёрной Лагуны \"",
            listOf("Экшен", "Сейнен"),
            R.drawable.black_lagoon, //Здесь данные специально поломаны для проверки работы вставки шаблонной картинки
            2006,
            "",
            24,
            mapOf(1 to 10, 2 to 5, 3 to 6, 4 to 8, 5 to 9, 6 to 5, 7 to 12, 8 to 22, 9 to 29, 10 to 38),
            listOf(1, 2, 3, 4, 7)
        ),

        Anime(
            7,
            "Триган", listOf("Приключения", "Драма", "Комедия"),
            1998,
            R.drawable.trigun,
            "",
            25,
            mapOf(1 to 10, 2 to 5, 3 to 6, 4 to 8, 5 to 9, 6 to 5, 7 to 12, 8 to 22, 9 to 29, 10 to 38),
            listOf(1, 2, 3, 4, 6)
        ),

        Anime(
            8,
            "Покемоны", listOf("Приключения", "Сёнен"),
            1997,
            R.drawable.pokemon,
            "",

            745,
            mapOf(1 to 10, 2 to 5, 3 to 6, 4 to 8, 5 to 9, 6 to 5, 7 to 12, 8 to 22, 9 to 29, 10 to 38),
            listOf(5, 10)
        ),

        Anime(
            9,
            "Психо-пасспорт",
            listOf("Мистика", "Драма", "Детектив"),
            2012,
            R.drawable.psycho_pass,
            "",
            26,
            mapOf(1 to 10, 2 to 5, 3 to 6, 4 to 8, 5 to 9, 6 to 5, 7 to 12, 8 to 22, 9 to 29, 10 to 38),
            listOf(1, 3, 10)
        ),

        Anime(
            10,
            "Детектив Конан",
            listOf("Детектив", "Комедия"),
            1996,
            R.drawable.conan_detective,
            "",
            1078,
            mapOf(1 to 10, 2 to 5, 3 to 6, 4 to 8, 5 to 9, 6 to 5, 7 to 12, 8 to 22, 9 to 29, 10 to 38),
            listOf(5, 9)

        )

    )


    override fun GetAnimeList() : List<Anime>
    {
        return animeList
    }

    override fun GetAnimeById(id: Int): Anime? {
        return animeList.find { it.id == id }
    }
}