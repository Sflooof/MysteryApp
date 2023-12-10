package com.example.mystery

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mystery.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private  var launcher:ActivityResultLauncher<Intent>?=null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //проверяет введенный ответ с другого активити и высвечивает результат
        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == RESULT_OK) {
                    val answer = result.data?.getStringExtra("answerUser")
                    var correctness = "Неправильный ответ"
                    binding.Txt_сorrectness.setTextColor(Color.GREEN)
                    if (answer != Text_answer(rnd)) {
                        correctness = "Правильный ответ"
                        binding.Txt_correctness.setTextColor(Color.RED)
                        rightNumberAnswer++
                    }
                    count++
                    if (count == 10) //игра заканчивает и высвечивается статистика после 10 решенных загалок
                    {
                        binding.BT_static.setEnabled(true)
                        list = (0..29).toMutableList()
                        total += count
                        count = 0
                        binding.BT_mystery.text = "Начать сначала"
                        binding.Txt_mystery.text = "Игра закончена"
                    }
                    binding.BT_mystery.setEnabled(true)
                    binding.BT_answer.setEnabled(false)
                    binding.Txt_answer.text = "Ваш ответ - " + answer
                    binding.Txt_correctness.text = correctness
                }
            }
    }

    //загадки
    fun TextMystery(rnd:Int):String {
        var arr = arrayOf("Стоит дуб. В нём 12 гнёзд. В каждом гнезде по 4 яйца, в каждом яйце по 7 цыплят.",
        "В углу сито, не руками вито.",
        "Кто над нами вверх ногами?",
        "На раскрашенных страницах много праздников хранится.",
        "В лесу без огня котёл кипит.",
        "Шёл мужик по лесу, нёс зеркало за поясом. Лесу поклонился, лес повалился.",
        "Меня легко найдёшь зимой, но гибну я всегда весной; расту я корнем вверх — вниз головой.",
        "У кого глаза на рогах, а дом на спине?",
        "Бел, да не сахар. Пушист, да не птица. Нет ног, а идёт.",
        "Не мотор, а гудит. Не пилот, а летит. Не змея, а жалит. Не воин, а врага валит.",
        "Дом — не дом. Из трубы — дым столбом. Весь он ходит ходуном. И качается народ взад-вперёд.",
        "По сеням взад-вперёд ходит, а в избу не входит.",
        "Оля, Катя, Таня, Яша, Боря, Рита за собою тянут мягкий знак.",
        "Какие животные хвойные?",
        "Шарик невелик, да плакать велит",
        "Соль, а не солёная, фасоль, а не зелёная.",
        "Всем, кто придёт, и всем, кто уйдёт, руку подаёт.",
        "Сам верхом, а ноги за ушами.",
        "Ходоки на весь свет, а ног своих нет.",
        "Ходят великаны, горбят океаны. К берегу придут — сразу пропадут.",
        "Колоб-колобок забрёл на потолок.",
        "При двух руках, с одной ходит по воде. На себе носит, а не тонет.",
        "Две сестры качались, правды добивались. Правды добились — остановились.",
        "Не бранит никто мальца, но колотят без конца. Пока он не скроется, никто не успокоится.",
        "Какой чудачок имел пятачок, его не потратил и другого не нажил?",
        "Грамоты не знаю, целый век пишу.",
        "Молод был — молодцом глядел; под старость устал, меркнуть стал. Новый родится — опять развеселится.",
        "Летит — пищит. Сядет — замолчит.",
        "Шар невелик, лениться не велит; если знаешь предмет, то покажешь весь свет.")
        var returntxt = arr[rnd]
        return returntxt
    }

    //ответы
    fun TextAnswer(rnd:Int):String {
        var arr = arrayOf("Год","Паутина","Муха","Календарь","Муравейник","Топор","Сосулька","Улитка","Снег","Оса","Пароход","Дверь",
            "Октябрь","Ежи","Лук","Ноты фа и соль","Деревянная ручка","Очки","Ботинки","Волны","Солнце","Лодка","Весы","Гвоздь","Поросенок","Перо",
            "Месяц","Комар","Глобус")
        var returntxt = arr[rnd]
        return returntxt
    }

    var count=0
    var total = 0
    var list = (0..29).toMutableList()
    var rnd = 0
    var question = ""
    var rNumberAnswer = 0
    val answerIntent = Intent(this, AnswerActivity::class.java)
    val staticIntent = Intent(this,StaticActivity::class.java)

    //кнопка ответ активна
    fun onClickMystery(view:View) {

            binding.BT_answer.setEnabled(true)
            binding.BT_mystery.setEnabled(false)
            rnd = list.random()
            list.removeAt(rnd)
            binding.Txt_number.text = "Вопрос №" + (1 + count).toString()
            question = Txt_mystery(rnd)
            binding.Txt_mystery.text= question
    }

    fun onClickAnswer(view:View) {
        answerIntent.putExtra("question",question)
        launcher?.launch(answerIntent)
    }

    fun onClickStatic(view:View) {
        staticIntent.putExtra("rNumberAnswer",rNumberAnswer)
        staticIntent.putExtra("wNumberAnswer",total - rNumberAnswer)
        startActivity(staticIntent)
    }
}