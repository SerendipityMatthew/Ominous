package me.xuwanjin.ominous.view

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import me.xuwanjin.ominous.activity.BugReportActivity
import androidx.compose.ui.window.DialogProperties as DialogProperties

@Composable
fun BugUploadView() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()

    ) {
        val (currentPeriodLog, selectPeriodLog, allDayLog,
            sendLogByEmail, defaultEmailHint, modifyEmail) = createRefs()
        Button(
            modifier = Modifier
                .width(BugReportActivity.BUTTON_WIDTH)
                .composed {
                    constrainAs(currentPeriodLog) {
                        start.linkTo(parent.start, margin = 40.dp)
                        end.linkTo(parent.end, margin = 40.dp)
                        top.linkTo(parent.top, margin = 10.dp)
                    }
                },
            onClick = { }
        ) {

            Text(
                text = "生成当前时间段的log",
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
        }
        Button(
            modifier = Modifier
                .width(BugReportActivity.BUTTON_WIDTH)
                .composed {
                    constrainAs(selectPeriodLog) {
                        start.linkTo(parent.start, margin = 40.dp)
                        end.linkTo(parent.end, margin = 40.dp)
                        top.linkTo(currentPeriodLog.bottom, margin = 10.dp)
                    }
                },
            onClick = { }
        ) {

            Text(
                text = "选择上传 log 的时间段",
                style = TextStyle(
                    fontSize = 18.sp
                )
            )
        }

        Button(
            modifier = Modifier
                .width(BugReportActivity.BUTTON_WIDTH)
                .composed {
                    constrainAs(allDayLog) {
                        start.linkTo(parent.start, margin = 40.dp)
                        end.linkTo(parent.end, margin = 40.dp)
                        top.linkTo(selectPeriodLog.bottom, margin = 10.dp)
                    }
                },
            onClick = { }) {

            Text(
                text = "压缩当天的所有log",
                style = TextStyle(
                    fontSize = 18.sp
                )
            )
        }
        Button(
            modifier = Modifier
                .width(BugReportActivity.BUTTON_WIDTH)
                .composed {
                    constrainAs(sendLogByEmail) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(allDayLog.bottom, margin = 10.dp)

                    }
                },
            onClick = { },
        ) {
            Text(
                text = "发送至邮箱",
                style = TextStyle(
                    fontSize = 18.sp
                )
            )
        }

        Text(
            text = "日志将被发送至该邮箱: \n${BugReportActivity.DEFAULT_LOG_RECEIVE_EMAIL}",
            modifier = Modifier
                .wrapContentSize()
                .composed {
                    constrainAs(defaultEmailHint) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(sendLogByEmail.bottom)
                    }
                },
            style = TextStyle(
                color = Color.Gray,
                fontSize = 10.sp
            )
        )
        val isShowDialog = remember { mutableStateOf<Boolean>(false) }

        Button(
            modifier = Modifier
                .width(BugReportActivity.BUTTON_WIDTH)
                .composed {
                    constrainAs(modifyEmail) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(defaultEmailHint.bottom, margin = 10.dp)
                    }
                },
            onClick = {
                isShowDialog.value = true
            }) {
            Text(
                text = "修改发送邮箱",
                style = TextStyle(
                    fontSize = 18.sp
                )
            )
        }
        ModifyLogReceiveEmailDialog(isShowDialog)
    }
}

@Composable
fun ModifyLogReceiveEmailDialog(isShowDialog: MutableState<Boolean>) {
    val inputEmailAddress = remember { mutableStateOf("") }
    val isSetDefaultEmail = remember { mutableStateOf(false) }
    val onDialogDismiss = { isShowDialog.value = false }
    if (!isShowDialog.value) {
        return
    }
    AlertDialog(
        onDismissRequest = onDialogDismiss,
        title = {
            Text(text = "收件人邮箱")
        },
        text = {
            ConstraintLayout(
            ) {
                val (inputEmail, setDefaultEmail) = createRefs()
                TextField(
                    value = inputEmailAddress.value,
                    onValueChange = {
                        inputEmailAddress.value = it
                    },
                    placeholder = {
                        Text(text = "请输入邮箱")
                    },
                    modifier = Modifier
                        .wrapContentSize()
                        .composed {
                            constrainAs(inputEmail) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                            }
                        }
                )
                Row(
                    modifier = Modifier.composed {
                        constrainAs(setDefaultEmail) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(inputEmail.bottom, margin = 10.dp)
                        }
                    }
                ) {
                    Checkbox(
                        checked = isSetDefaultEmail.value,
                        onCheckedChange = {
                            Log.d("Matthew", "ModifyLogReceiveEmailDialog: it = $it")
                            isSetDefaultEmail.value = it
                        }

                    )
                    Text(
                        text = "并将此邮箱设置为默认邮箱？",
                        modifier = Modifier.padding(start = 5.dp)

                    )
                }
            }
        },
        shape = RoundedCornerShape(10.dp),
        dismissButton = {
            Button(
                onClick = onDialogDismiss,
                modifier = Modifier.padding(start = 0.dp)
            ) {
                Text(text = "取消")
            }
        },
        confirmButton = {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "确定")
            }
        },
        properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true
        )
    )

}