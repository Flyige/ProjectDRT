package com.flyige.projectdrt.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flyige.projectdrt.R
import com.flyige.projectdrt.beans.DailyInfo
import com.flyige.projectdrt.beans.DailyMeals
import com.flyige.projectdrt.beans.DailyTraining
import com.flyige.projectdrt.constants.FOOD_TYPE_MILD
import com.flyige.projectdrt.constants.FOOD_TYPE_OILY
import com.flyige.projectdrt.constants.TRAINING_TYPE_HIGH
import com.flyige.projectdrt.constants.TRAINING_TYPE_LOW
import com.flyige.projectdrt.viewmodels.DailyInfoViewModel
import java.time.LocalDate

/**
 * @author: yige
 * @params:
 * @date: 2024 2024/2/2 11:40
 */

// date.value-> {breakfast:[value ,type ],lunch:[value ,type ] , supper :[value ,type]}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyInfoScreen(dailyInfoViewModel: DailyInfoViewModel) {
    //和 viewmodel 保持一致
    var date = LocalDate.now().toString()
    var meals = listOf<String>(
        stringResource(id = R.string.item_daily_food_breakfast),
        stringResource(id = R.string.item_daily_food_lunch),
        stringResource(id = R.string.item_daily_food_supper),
        stringResource(id = R.string.item_daily_food_other)
    )
    var dailyMeals = mutableListOf<DailyMeals>()
    var dailyTraining = DailyTraining("", -1)// default useless value
    Column(
        modifier = Modifier
            .padding(10.dp)
            .background(Color.White)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        // TODO: 而且要有 check 输入错误的逻辑考虑进去
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Text(text = stringResource(id = R.string.item_daily_date))
            Text(text = date)
        }
        meals.forEach { content ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                var meal by remember {
                    mutableStateOf("")
                }
                var mealType: Int = 0
                OutlinedTextField(
                    modifier = Modifier.wrapContentHeight(),
                    value = meal,
                    onValueChange = { meal = it },
                    label = { Text(text = content) })
                mealType = DailyInfoMealType()
                dailyMeals.add(DailyMeals(meal = meal, mealType = mealType))
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            var training: String = ""
            var trainingIntensity: Int = 0
            OutlinedTextField(
                modifier = Modifier.wrapContentHeight(),
                value = training,
                onValueChange = { training = it },
                label = { Text(text = "训练") }
            )
            trainingIntensity = dailyTrainingIntensity()
            dailyTraining = DailyTraining(training, trainingIntensity)
        }
        ElevatedButton(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
            onClick = { /*TODO*/ }){
            Text(text = "提交")
        }
        var dailyInfo = DailyInfo(date)
        dailyInfo.beakfast = dailyMeals[0]
        dailyInfo.lunch = dailyMeals[1]
        dailyInfo.supper = dailyMeals[2]
        dailyInfo.other = dailyMeals[3]
        dailyInfo.training = dailyTraining
        Log.d("fyg  ", "DailyInfoScreen: $dailyInfo")
        dailyInfoViewModel.addDailyInfo(dailyInfo)
    }

}

// TODO: 其实可以自己做个组件服用这种 radiogroup 
@Composable
fun DailyInfoMealType(): Int {
    val radioOptions = listOf(
        stringResource(id = R.string.item_daily_food_type_oily),
        stringResource(id = R.string.item_daily_food_type_mild)
    )
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }
    Column() {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(28.dp),
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
    return if (selectedOption == stringResource(id = R.string.item_daily_food_type_oily)) FOOD_TYPE_OILY else FOOD_TYPE_MILD
}

@Composable
fun dailyTrainingIntensity(): Int {
    val radioOptions = listOf(
        stringResource(id = R.string.item_daily_training_intensity_high),
        stringResource(id = R.string.item_daily_training_intensity_low)
    )
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }
    Column {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(28.dp),
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
    return if (selectedOption == stringResource(id = R.string.item_daily_training_intensity_high)) TRAINING_TYPE_HIGH else TRAINING_TYPE_LOW

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun show() {
    var viewModel = DailyInfoViewModel()
    DailyInfoScreen(viewModel)

}
