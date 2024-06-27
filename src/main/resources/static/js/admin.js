function showPage(pageId) {
  // Hide all pages
  var pages = document.querySelectorAll('[id$="-page"]');
  pages.forEach(function(page) {
    page.style.display = 'none';
  });

  // Show the specified page
  document.getElementById(pageId).style.display = 'block';
}

// Event listeners to navigate to different pages
document.getElementById('dashboard-link').addEventListener('click', function(event) {
  event.preventDefault();
  showPage('dashboard-page');
});

document.getElementById('student-link').addEventListener('click', function(event) {
  event.preventDefault();
  showPage('student-page');
});

document.getElementById('question-link').addEventListener('click',
  showPage('questions-page');
);

// Show the default page (Dashboard) when the page loads
showPage('dashboard-page');