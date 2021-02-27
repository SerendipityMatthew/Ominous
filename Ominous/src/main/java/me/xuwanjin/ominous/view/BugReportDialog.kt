package me.xuwanjin.ominous.view

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import me.xuwanjin.ominous.R

@Composable
fun BugReportDialog(context: Context) {
    ConstraintLayout(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White, RoundedCornerShape(20.dp))
    ) {
        val (noShowAgain, upContentView, closeDialog, bugReportTitle,
            bugReportContent, directReport, screenshotReport)
                = createRefs()

        Text(
            text = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(34, 137, 255))
                .composed {
                    constrainAs(upContentView) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
                },
        )

        Text(text = context.resources.getString(R.string.no_show_again),
            modifier = Modifier
                .clickable {

                }
                .wrapContentSize()
                .composed {
                    constrainAs(noShowAgain) {
                        start.linkTo(parent.start, margin = 10.dp)
                        top.linkTo(parent.top, margin = 10.dp)
                    }
                },
            style = TextStyle(
                color = Color.Black,
            )
        )

        Image(
            bitmap = ImageBitmap(0,0),
            modifier = Modifier
                .height(15.dp)
                .width(15.dp)
                .clickable {

                }
                .composed {
                    constrainAs(closeDialog) {
                        end.linkTo(parent.end, margin = 10.dp)
                        top.linkTo(parent.top, margin = 10.dp)
                    }
                },
            contentDescription = ""
        )


        Text(
            text = context.resources.getString(R.string.bug_report_title),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable {

                }
                .composed {
                    constrainAs(bugReportTitle) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(upContentView.bottom, 20.dp)
                    }
                },

            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            ),
        )

        Text(
            text = context.resources.getString(R.string.bug_report_content),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
                .wrapContentHeight()
                .composed {
                    constrainAs(bugReportContent) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(bugReportTitle.bottom, 20.dp)
                    }
                },

            style = TextStyle(
                color = Color.Black,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
            ),
        )
        Text(
            text = context.resources.getString(R.string.direct_bugreport),
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp, end = 20.dp)

                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(color = Color(235, 240, 255))
                .clickable {

                }
                .wrapContentHeight(align = Alignment.CenterVertically)
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .composed {
                    constrainAs(directReport) {
                        top.linkTo(bugReportContent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                },

            style = TextStyle(
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                fontFamily = FontFamily.Serif
            )
        )
        Text(
            text = context.resources.getString(R.string.screenshot_bugreport),
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 40.dp)

                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(color = Color(235, 240, 255))
                .clickable {

                }
                .wrapContentHeight(align = Alignment.CenterVertically)
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .composed {
                    constrainAs(screenshotReport) {
                        top.linkTo(directReport.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                },
            style = TextStyle(
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                fontFamily = FontFamily.Serif
            )
        )
    }
}
