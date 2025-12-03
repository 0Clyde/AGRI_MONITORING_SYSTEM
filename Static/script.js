function goToHomepage() { window.location.href = "Homepage.html"; }
function goToSignup() { window.location.href = "Signup.html"; }
function goToLogin() { window.location.href = "Login.html"; }
function goToForgotpassword() { window.location.href = "Forgotpassword.html"; }
function goToInterface() { window.location.href = "Interface.html"; }
function goToProfile() { window.location.href = "Profile.html"; }
function goToEditProfile() { window.location.href = "EditProfile.html"; }
function goToLivestock_Tables() { window.location.href = "Livestock_Tables.html"; }
function goToCrops_Tables() { window.location.href = "Crops_Tables.html"; }
function goToFisheries_Tables() { window.location.href = "Fisheries_Tables.html"; }
function goToLivestock_Overview() { window.location.href = "Livestock_Overview.html"; }
function logout() { window.location.href = "LogoutServlet"; }


function logout() {
    const confirmLogout = confirm("Are you sure you want to logout?");
    if (confirmLogout) {
       
        sessionStorage.clear();
        localStorage.clear();
     
        window.location.href = "login.html"; 
    }
}



document.addEventListener("DOMContentLoaded", function() {
    const togglePassword = document.getElementById("togglePassword");
    if (togglePassword) {
        togglePassword.addEventListener("click", () => {
            const passwordInput = document.getElementById("password");
            passwordInput.type = passwordInput.type === "password" ? "text" : "password";
            togglePassword.classList.toggle('active'); 
        });
    }

    const toggleConfirmPassword = document.getElementById("toggleConfirmPassword");
    if (toggleConfirmPassword) {
        toggleConfirmPassword.addEventListener("click", () => {
            const confirmPwdInput = document.getElementById("confirmPassword");
            confirmPwdInput.type = confirmPwdInput.type === "password" ? "text" : "password";
            toggleConfirmPassword.classList.toggle('active');
        });
    }

   
    const urlParams = new URLSearchParams(window.location.search);
    const error = urlParams.get('error');
    const errorMessage = document.getElementById('errorMessage');

    if (errorMessage) {
        if (error === 'invalid') {
            errorMessage.textContent = 'Invalid username or password. Please try again.';
            errorMessage.classList.add('show');
        } else if (error === 'system') {
            errorMessage.textContent = 'System error. Please try again later.';
            errorMessage.classList.add('show');
        } else if (error === 'session') {
            errorMessage.textContent = 'Session expired. Please login again.';
            errorMessage.classList.add('show');
        }
    }

  
    const forgotLink = document.querySelector('.forgot-password');
    if (forgotLink) {
        forgotLink.addEventListener('click', (e) => {
            e.preventDefault();
            goToForgotpassword(); 
        });
    }
});

// Handle form submission to save profile
function saveProfile(event) {
  event.preventDefault(); // Prevent the form from refreshing the page

  const fullName = document.getElementById('fullName').value;
  const username = document.getElementById('username').value;
  const email = document.getElementById('email').value;
  const password = document.getElementById('password').value;
  const confirmPassword = document.getElementById('confirmPassword').value;

  // Password validation
  if (password !== confirmPassword) {
    alert("Passwords do not match!");
    return;
  }

  // Update the profile information (In a real application, you would send this data to a server)
  console.log("Profile Updated");
  console.log(`Full Name: ${fullName}`);
  console.log(`Username: ${username}`);
  console.log(`Email: ${email}`);
  console.log(`New Password: ${password}`);

  alert("Profile updated successfully!");
}

// Additional logic to handle file input for profile picture (optional)
function handleFileInput(event) {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = function () {
      document.querySelector('.current-pic img').src = reader.result;
    };
    reader.readAsDataURL(file);
  }
}

// Listen for changes to the profile picture input
document.getElementById('profilePic').addEventListener('change', handleFileInput);



function displayTable(tableName) {
    switch(tableName) {
        case 'crops':
            goToCrops_Tables();
            break;
        case 'fisheries':
            goToFisheries_Tables();
            break;
        case 'livestock':
            goToLivestock_Tables();
            break;
        default:
            console.error('Unknown table:', tableName);
    }
}

function openOverview(barangayName) {
    
    window.location.href = `crops_overview.html?barangay=${encodeURIComponent(barangayName)}`;
}


function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}



function goToFisheriesOverview(barangayName) {
    
    window.location.href = `fisheries_overview.html?barangay=${encodeURIComponent(barangayName)}`;
}


function logout() {
    const confirmLogout = confirm("Are you sure you want to logout?");
    if (confirmLogout) {
       
        sessionStorage.clear();
        localStorage.clear();
        
        window.location.href = "login.html"; 
    }
}


function displayTable(tableName) {
    switch(tableName) {
        case 'crops':
            goToCrops_Tables();
            break;
        case 'fisheries':
            goToFisheries_Tables();
            break;
        case 'livestock':
            goToLivestock_Tables();
            break;
        default:
            console.error('Unknown table:', tableName);
    }
}

function openOverview(barangayName) {
    
    window.location.href = `crops_overview.html?barangay=${encodeURIComponent(barangayName)}`;
}


function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}




function goToLivestockOverview(barangayName) {
   
    window.location.href = `livestock_overview.html?barangay=${encodeURIComponent(barangayName)}`;
}


document.addEventListener("DOMContentLoaded", function() {
    const togglePassword = document.getElementById("togglePassword");
    if (togglePassword) {
        togglePassword.addEventListener("click", () => {
            const passwordInput = document.getElementById("password");
            passwordInput.type = passwordInput.type === "password" ? "text" : "password";
            togglePassword.classList.toggle('active');
        });
    }

    const toggleConfirmPassword = document.getElementById("toggleConfirmPassword");
    if (toggleConfirmPassword) {
        toggleConfirmPassword.addEventListener("click", () => {
            const confirmPwdInput = document.getElementById("confirmPassword");
            confirmPwdInput.type = confirmPwdInput.type === "password" ? "text" : "password";
            toggleConfirmPassword.classList.toggle('active');
        });
    }
});


function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}


document.addEventListener("DOMContentLoaded", function() {
    const barangay = getQueryParam("barangay");
    console.log("Barangay: " + barangay); 
});

// Event Listener for Update button
document.getElementById("updateBtn").addEventListener("click", async () => {
    const barangay = getQueryParam("barangay");
    const farmerId = getQueryParam("farmerId");

    const cropData = {};
    document.querySelectorAll("#editFarmerForm input[name]").forEach(input => {
        cropData[input.name] = input.value; // Collecting form data
    });

    try {
        // Sending PUT request to backend
        const response = await fetch(`/api/crops/${barangay}/${farmerId}`, {
            method: "PUT", // Use PUT for updates
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(cropData)
        });

        if (response.ok) {
            alert("Data updated successfully!");
            window.location.href = `Crops_Overview.html?barangay=${barangay}`; // Go back to overview page
        } else {
            alert("Failed to update data.");
        }
    } catch (error) {
        console.error("Error updating data:", error);
        alert("An error occurred while updating data.");
    }
});

document.getElementById("addNewBtn").addEventListener("click", () => {
    const barangay = getQueryParam("barangay") || "anao"; // Grab the barangay value
    window.location.href = `Fish_fillup.html?barangay=${barangay}`; // Redirect to the fill-up page
});

function goBack() {
    const barangay = getQueryParam("barangay") || "anao"; // Get barangay
    window.location.href = `Fisheries_Tables.html?barangay=${barangay}`; // Redirect to the overview page
}

// livestock.js - shared functions for overview, fillup, and details pages

// Utility: get query param
function getQueryParam(param) {
  const urlParams = new URLSearchParams(window.location.search);
  return urlParams.get(param);
}

// -------------------------------
// Overview functions
// -------------------------------
async function loadLivestockFarmers(barangay) {
  const container = document.getElementById('farmersList');
  if (!container) return;
  container.innerHTML = '<div class="loading">Loading...</div>';

  try {
    const resp = await fetch(`/api/livestock/${encodeURIComponent(barangay)}`);
    if (!resp.ok) throw new Error('Failed to fetch farmers');
    const list = await resp.json();
    container.innerHTML = '';

    if (!list || list.length === 0) {
      container.innerHTML = '<div class="farmer-item empty">No data yet.</div>';
      return;
    }

    list.forEach(farmer => {
      const row = document.createElement('div');
      row.className = 'farmer-item';

      const link = document.createElement('a');
      link.className = 'farmer-link';
      // prefer farmer_name field if provided by backend
      const name = farmer.farmer_name || farmer.farmerName || 'Unnamed Farmer';
      link.href = `FarmersLivestockDetails.html?barangay=${encodeURIComponent(barangay)}&farmerId=${farmer.id}`;
      link.textContent = name;

      const delBtn = document.createElement('button');
      delBtn.className = 'delete-farmer-btn';
      delBtn.title = 'Delete farmer';
      delBtn.innerHTML = '🗑️';
      delBtn.addEventListener('click', async (e) => {
        e.preventDefault();
        e.stopPropagation();
        const ok = confirm(`Delete farmer "${name}"?`);
        if (!ok) return;
        try {
          const dresp = await fetch(`/api/livestock/${encodeURIComponent(barangay)}/${farmer.id}`, {
            method: 'DELETE'
          });
          if (!dresp.ok) throw new Error('Delete failed');
          // refresh
          await loadLivestockFarmers(barangay);
        } catch (err) {
          console.error(err);
          alert('Error deleting farmer');
        }
      });

      row.appendChild(link);
      row.appendChild(delBtn);
      container.appendChild(row);
    });
  } catch (err) {
    console.error(err);
    container.innerHTML = '<div class="error">Error loading farmers</div>';
  }
}

// -------------------------------
// Create new record (fillup page)
// -------------------------------
async function createLivestockRecord(barangayFromUrl) {
  // collect inputs; use camelCase keys to match your Java bean
  const payload = {
    farmerName: (document.getElementById('farmerName') || {}).value || '',
    address: (document.getElementById('address') || {}).value || (barangayFromUrl || ''),
    barangay: (document.getElementById('address') || {}).value || (barangayFromUrl || ''),
    contact: (document.getElementById('contact') || {}).value || '',
    age: parseInt((document.getElementById('age') || {}).value || '0', 10) || 0,
    sex: (document.getElementById('sex') || {}).value || ''
  };

  // numeric animal fields - defined by your model
  const numericFields = [
    'cattle_male','cattle_female','carabao_male','carabao_female','horse_male','horse_female',
    'goat_male','goat_female','sheep_male','sheep_female','swine_male','swine_female',
    'chicken_male','chicken_female','duck_male','duck_female','quail_male','quail_female',
    'turkey_male','turkey_female','geese_male','geese_female','dove_male','dove_female',
    'ostrich_male','ostrich_female','guinea_fowl_male','guinea_fowl_female','game_fowl_male','game_fowl_female',
    'rabbit_male','rabbit_female','dog_male','dog_female','cat_male','cat_female'
  ];

  numericFields.forEach(id => {
    const el = document.getElementById(id);
    let val = 0;
    if (el && el.value !== '') {
      val = parseInt(el.value, 10);
      if (Number.isNaN(val)) val = 0;
    }
    // convert snake_case field name to camelCase for Java mapping if necessary
    // But your Java fields are snake_case in names (getCattle_male), Jackson will bind either
    // We'll send as camelCase-ish matching your JS ids (they're snake) - server will accept.
    payload[id] = val;
  });

  try {
    const targetBarangay = (barangayFromUrl && barangayFromUrl !== '') ? barangayFromUrl : (payload.barangay || '');
    const resp = await fetch(`/api/livestock/${encodeURIComponent(targetBarangay)}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload)
    });
    if (!resp.ok) {
      const txt = await resp.text();
      throw new Error(txt || 'Failed to save');
    }
    const created = await resp.json();
    alert('Saved successfully!');
    // redirect to overview
    window.location.href = `Livestock_Overview.html?barangay=${encodeURIComponent(targetBarangay)}`;
  } catch (err) {
    console.error('Error creating livestock record', err);
    alert('Failed to save data. See console for details.');
  }
}

// -------------------------------
// Details page: load farmer + render table
// -------------------------------
async function loadFarmerDetails(barangay, farmerId) {
  const tbody = document.getElementById('livestockTableBody');
  if (!tbody) return;
  tbody.innerHTML = '<tr><td colspan="4">Loading...</td></tr>';

  try {
    const resp = await fetch(`/api/livestock/${encodeURIComponent(barangay)}/${encodeURIComponent(farmerId)}`);
    if (!resp.ok) throw new Error('Failed to fetch farmer details');
    const data = await resp.json();

    // header info
    document.getElementById('farmerName').textContent = data.farmer_name || (data.raw && data.raw.farmerName) || '—';
    document.getElementById('farmerBarangay').textContent = data.barangay || (data.raw && (data.raw.barangay || data.raw.address)) || '—';
    document.getElementById('farmerIdLabel').textContent = data.id || farmerId || '—';

    // render livestock rows
    const animals = data.livestock || [];
    renderLivestockRows(animals, /*editMode*/ false);
  } catch (err) {
    console.error(err);
    tbody.innerHTML = '<tr><td colspan="4">Error loading data.</td></tr>';
  }
}

function renderLivestockRows(animals, editMode) {
  const tbody = document.getElementById('livestockTableBody');
  if (!tbody) return;
  tbody.innerHTML = '';

  if (!animals || animals.length === 0) {
    tbody.innerHTML = '<tr><td colspan="4">No livestock records yet.</td></tr>';
    return;
  }

  animals.forEach((a, idx) => {
    const tr = document.createElement('tr');
    if (!editMode) {
      tr.innerHTML = `<td></td>
                      <td>${escapeHtml(a.animal_type)}</td>
                      <td>${a.male != null ? a.male : 0}</td>
                      <td>${a.female != null ? a.female : 0}</td>`;
    } else {
      tr.innerHTML = `<td><button class="row-delete" data-index="${idx}">✕</button></td>
                      <td><input class="animal-type" data-index="${idx}" value="${escapeHtml(a.animal_type)}"></td>
                      <td><input class="animal-male" data-index="${idx}" type="number" min="0" value="${a.male != null ? a.male : 0}"></td>
                      <td><input class="animal-female" data-index="${idx}" type="number" min="0" value="${a.female != null ? a.female : 0}"></td>`;
    }
    tbody.appendChild(tr);
  });

  if (editMode) {
    // delegate delete row
    tbody.querySelectorAll('.row-delete').forEach(btn => {
      btn.addEventListener('click', (e) => {
        const idx = parseInt(btn.dataset.index, 10);
        if (Number.isNaN(idx)) return;
        // remove corresponding row from DOM
        const inputRows = Array.from(tbody.querySelectorAll('tr'));
        if (inputRows[idx]) inputRows[idx].remove();
        // re-index data-index attributes for remaining inputs
        reindexEditRows();
      });
    });
  }
}

// escape small helper
function escapeHtml(s) {
  if (s == null) return '';
  return String(s).replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/"/g,'&quot;');
}

// -------------------------------
// Edit mode helpers for details page
// -------------------------------
function enterEditMode() {
  // show edit actions
  document.getElementById('editActions').style.display = 'block';
  // convert rendered rows to inputs if not already
  const currentRows = Array.from(document.querySelectorAll('#livestockTableBody tr'));
  if (currentRows.length === 0) return;
  const animals = [];
  currentRows.forEach(r => {
    const cols = r.querySelectorAll('td');
    // if already inputs skip
    if (r.querySelector('input')) {
      // keep current row as-is
      return;
    }
    const animalType = cols[1].textContent || '';
    const male = parseInt(cols[2].textContent || '0', 10) || 0;
    const female = parseInt(cols[3].textContent || '0', 10) || 0;
    animals.push({ animal_type: animalType, male, female });
  });

  // re-render rows as edit
  // If animals array empty (maybe rows already had inputs) try collecting inputs already present
  const rowsToRender = animals.length ? animals : collectAnimalsFromTable();
  renderLivestockRows(rowsToRender, true);
}

function collectAnimalsFromTable() {
  const rows = Array.from(document.querySelectorAll('#livestockTableBody tr'));
  const animals = [];
  rows.forEach(r => {
    const tds = r.querySelectorAll('td');
    if (tds.length >= 4) {
      animals.push({
        animal_type: tds[1].textContent || '',
        male: parseInt(tds[2].textContent || '0', 10) || 0,
        female: parseInt(tds[3].textContent || '0', 10) || 0
      });
    }
  });
  return animals;
}

function reindexEditRows() {
  const rows = Array.from(document.querySelectorAll('#livestockTableBody tr'));
  rows.forEach((r, i) => {
    r.querySelectorAll('input').forEach(inp => inp.dataset.index = i);
    const btn = r.querySelector('.row-delete');
    if (btn) btn.dataset.index = i;
  });
}

function addEmptyAnimalRow() {
  const tbody = document.getElementById('livestockTableBody');
  if (!tbody) return;
  const idx = tbody.querySelectorAll('tr').length;
  const tr = document.createElement('tr');
  tr.innerHTML = `<td><button class="row-delete" data-index="${idx}">✕</button></td>
                  <td><input class="animal-type" data-index="${idx}" value=""></td>
                  <td><input class="animal-male" data-index="${idx}" type="number" min="0" value="0"></td>
                  <td><input class="animal-female" data-index="${idx}" type="number" min="0" value="0"></td>`;
  tbody.appendChild(tr);
  reindexEditRows();
  // wire delete for new row
  tr.querySelector('.row-delete').addEventListener('click', (e) => {
    tr.remove();
    reindexEditRows();
  });
}

function exitEditMode() {
  document.getElementById('editActions').style.display = 'none';
}

// -------------------------------
// Save changes from details page (PUT)
// -------------------------------
async function saveFarmerLivestockChanges(barangay, farmerId) {
  // collect farmer info (if edit allowed) - but our UI currently edits only livestock rows.
  const name = document.getElementById('farmerName').textContent || '';
  const barangayVal = document.getElementById('farmerBarangay').textContent || barangay || '';

  // collect rows from edit inputs
  const rows = Array.from(document.querySelectorAll('#livestockTableBody tr'));
  const livestockArray = rows.map(r => {
    const typeInput = r.querySelector('input.animal-type');
    const maleInput = r.querySelector('input.animal-male');
    const femaleInput = r.querySelector('input.animal-female');

    if (!typeInput) {
      // not in edit mode; fallback to reading textCells
      const tds = r.querySelectorAll('td');
      return {
        animal_type: tds[1] ? tds[1].textContent.trim() : '',
        male: parseInt(tds[2] ? tds[2].textContent.trim() : '0', 10) || 0,
        female: parseInt(tds[3] ? tds[3].textContent.trim() : '0', 10) || 0
      };
    }
    return {
      animal_type: typeInput.value.trim(),
      male: parseInt(maleInput.value || '0', 10) || 0,
      female: parseInt(femaleInput.value || '0', 10) || 0
    };
  });

  // Build payload expected by backend: update the numeric fields in Livestock model
  // We will translate livestockArray back to the flat numeric fields like cattle_male, etc.
  // Start empty payload and set farmer info fields that controller will accept.
  const payload = {
    farmerName: name,
    barangay: barangayVal
    // numeric fields set below
  };

  // helper to map animal_type -> key usage
  function normalizeAnimalKey(animalName) {
    // lower-case, replace spaces with underscore, remove non-alpha
    return animalName.toLowerCase().replace(/\s+/g, '_').replace(/[^a-z0-9_]/g,'');
  }

  // init all numeric fields to 0 (so PUT will overwrite existing)
  const numericFields = [
    'cattle', 'carabao', 'horse', 'goat', 'sheep','swine','chicken','duck',
    'quail','turkey','geese','dove','ostrich','guinea_fowl','game_fowl',
    'rabbit','dog','cat'
  ];
  numericFields.forEach(base => {
    payload[`${base}_male`] = 0;
    payload[`${base}_female`] = 0;
  });

  // populate from table
  livestockArray.forEach(a => {
    const base = normalizeAnimalKey(a.animal_type || '');
    if (!base) return;
    // common mappings: people often use "bangus" etc; we try exact base if in list else set custom (will still be saved to raw)
    if (numericFields.includes(base)) {
      payload[`${base}_male`] = a.male || 0;
      payload[`${base}_female`] = a.female || 0;
    } else {
      // if custom or different naming, attempt common synonyms:
      // e.g., "guinea fowl" -> "guinea_fowl" (normalizeAnimalKey already does that)
      if (numericFields.includes(base)) {
        payload[`${base}_male`] = a.male || 0;
        payload[`${base}_female`] = a.female || 0;
      } else {
        // If not in known list, add to payload as dynamic field name (controller will ignore unknown unless model has it)
        payload[`${base}_male`] = a.male || 0;
        payload[`${base}_female`] = a.female || 0;
      }
    }
  });

  try {
    const resp = await fetch(`/api/livestock/${encodeURIComponent(barangay)}/${encodeURIComponent(farmerId)}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload)
    });
    if (!resp.ok) {
      const txt = await resp.text();
      throw new Error(txt || 'Failed to save updates');
    }
    alert('Saved successfully');
    exitEditMode();
    // reload details
    await loadFarmerDetails(barangay, farmerId);
  } catch (err) {
    console.error(err);
    alert('Failed to save changes. Check console.');
  }
}

