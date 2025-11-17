package com.example.spotify.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.spotify.Book
import com.example.spotify.BooksByCategory
import com.example.spotify.R
import com.example.spotify.viewmodels.MainBooksViewModel
import java.time.format.TextStyle

@Composable
fun Dashboard() {
    val scrollState = rememberScrollState()
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth().verticalScroll(scrollState)) {
        Row(modifier = Modifier.fillMaxWidth(0.88f).padding(top = 65.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically ) {
            Text(text = "Happy Reading!", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

            Text(text="", modifier = Modifier.size(25.dp))
        }

        Row(modifier = Modifier.fillMaxWidth(0.88f).padding(top = 53.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically ) {
            Text(text = "Top Books", fontSize = 20.sp)

            Text(text="see more", fontSize = 14.sp, color = Color(0xff121212))
        }
        BookListApiResult("popular")
        Row(modifier = Modifier.fillMaxWidth(0.88f).padding(top = 53.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically ) {
            Text(text = "Top Books", fontSize = 20.sp)

            Text(text="see more", fontSize = 14.sp, color = Color(0xff121212))
        }
        BookListApiResult("classics")
        Row(modifier = Modifier.fillMaxWidth(0.88f).padding(top = 53.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically ) {
            Text(text = "Top Books", fontSize = 20.sp)

            Text(text="see more", fontSize = 14.sp, color = Color(0xff121212))
        }
        BookListApiResult("children")
        Row(modifier = Modifier.fillMaxWidth(0.88f).padding(top = 53.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically ) {
            Text(text = "Top Books", fontSize = 20.sp)

            Text(text="see more", fontSize = 14.sp, color = Color(0xff121212))
        }
        BookListApiResult("novela")
        Row(modifier = Modifier.fillMaxWidth(0.88f).padding(top = 53.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically ) {
            Text(text = "Top Books", fontSize = 20.sp)

            Text(text="see more", fontSize = 14.sp, color = Color(0xff121212))
        }
        BookListApiResult("fantasy")


    }



}

@Composable
fun BookList(books: List<Book>) {
    Spacer(modifier = Modifier.height(32.dp))
    LazyHorizontalGrid(
        modifier = Modifier.height(288.dp).padding(start = 12.dp),
        rows = GridCells.Fixed(1),
        horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        items(books) { book ->

            SingleBook(book)
        }
    }
}


@Composable
fun BookListApiResult(category: String) {
    val viewModel: MainBooksViewModel = hiltViewModel(key = category)

    LaunchedEffect(category) {
        viewModel.loadBooks(category)
    }

    val viewState by viewModel.viewState.collectAsState()

    when (viewState) {
        is MainBooksViewModel.ViewState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize().padding(top=30.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                CircularProgressIndicator(
                    color = Color.Gray
                )
            }
        }

        is MainBooksViewModel.ViewState.Error -> {
            println("eror")
        }

        is MainBooksViewModel.ViewState.Success -> {
            val books = (viewState as MainBooksViewModel.ViewState.Success).data?.works ?: emptyList()
            println(books)
            BookList(books)

        }
    }
}

@Composable
fun SingleBook(book: Book) {
    println(book.coverEditionKey)
    val imageUrl = book.coverEditionKey?.let { "https://covers.openlibrary.org/b/olid/${it}-M.jpg" }
        ?: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSidQoav1MLzs-vLXRgx7f4S-16yT0D4YB2A&s"
    Column(modifier = Modifier.width(180.dp).clip(RoundedCornerShape(5.dp))) {
        Box(modifier = Modifier.fillMaxWidth().height(140.dp).background(Color(0xffB8B8B8)), contentAlignment = Alignment.TopCenter){
            AsyncImage(
                contentDescription = "book image",
                model = imageUrl,
                modifier = Modifier.height( 140.dp).width(91.dp).offset(y = 11.dp)
            )
        }
        Box(modifier = Modifier.fillMaxWidth().height(148.dp).background(Color(0xff000000))) {
            Column(modifier = Modifier.fillMaxWidth(0.88f)) {
                Text(text = book.title ?: "title", fontSize = 16.sp, color = Color(0xffffffff),modifier = Modifier.padding(top = 2.dp, start = 10.dp))
                Text(text = book.authors?.firstOrNull()?.name ?: "Unknown", fontSize = 11.sp, color = Color(0xffffffff), modifier = Modifier.padding(top = 8.dp, start = 10.dp))
            }

        }
    }
}

//@Preview
//@Composable
//fun SingleBookPreviews() {
//    SingleBook()
//}