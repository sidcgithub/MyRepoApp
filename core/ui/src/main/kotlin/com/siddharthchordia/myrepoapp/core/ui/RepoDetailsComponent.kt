package com.siddharthchordia.myrepoapp.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.siddharthchordia.myrepoapp.core.model.data.Repo
import com.siddharthchordia.myrepoapp.core.model.data.RepoDetails

@Composable
fun RepoDetailsComponent(repoDetailsUiState: RepoDetailsUiState.Success) {
    val repo = repoDetailsUiState.repoDetails.repo
    val totalForks = repoDetailsUiState.repoDetails.totalForks
    val shouldShowBadge = totalForks > 5000
    val badgeColor = if (shouldShowBadge) Color.Red else Color.Unspecified

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                dimensionResource(
                    id = R.dimen.padding_medium,
                ),
            ),
        elevation = CardDefaults.cardElevation(
            dimensionResource(
                id = R.dimen.padding_small,
            ),
        ),
    ) {
        Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = repo.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                if (shouldShowBadge) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star badge",
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
                        tint = Color(0xFFD4AF37),
                    )
                }
                Text(
                    text = "${repo.forks} forks out of $totalForks",
                    color = badgeColor,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
                )
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
            Text(
                text = repo.description ?: "No description available",
                style = MaterialTheme.typography.bodyLarge,
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
            Text(text = stringResource(R.string.additional_details_labels), style = MaterialTheme.typography.bodyLarge)
            Text(
                text = stringResource(R.string.last_updated_label, repo.updatedAt),
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = stringResource(R.string.stargazers_label, repo.stargazersCount),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

sealed interface RepoDetailsUiState {
    data object Loading : RepoDetailsUiState
    data object LoadFailed : RepoDetailsUiState

    data class Success(
        val repoDetails: RepoDetails,
    ) : RepoDetailsUiState
}

@Preview(showBackground = true)
@Composable
fun RepoDetailScreenPreview() {
    RepoDetailsComponent(
        RepoDetailsUiState.Success(
            repoDetails = RepoDetails(
                repo = Repo(
                    name = "MyRepo",
                    description = "This is a sample repo",
                    stargazersCount = 100,
                    forks = 50,
                    updatedAt = "2021-09-01",
                ),
                totalForks = 1000,
            ),
        ),
    )
}
