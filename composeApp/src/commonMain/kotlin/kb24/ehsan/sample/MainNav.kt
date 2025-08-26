package kb24.ehsan.sample

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kb24.ehsan.sample.screen.first.FirstRout
import kb24.ehsan.sample.screen.first.FirstScreen
import kb24.ehsan.sample.screen.scond.SecondRout
import kb24.ehsan.sample.screen.scond.SecondScreen

@Composable
fun MainNav(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = FirstRout,
    ){
        composable<FirstRout> {
            FirstScreen(
                navigateNext = { navController.navigate(SecondRout) }
            )
        }
        composable<SecondRout> {
            SecondScreen()
        }
    }
}