// ===== Dark mode toggle =====
(function () {
    const root = document.body;
    const key = 'nt-theme';
    const saved = localStorage.getItem(key);

    const setTheme = (theme) => {
        root.setAttribute('data-bs-theme', theme);
        localStorage.setItem(key, theme);
        document.querySelector('#themeToggle i')?.classList
            .toggle('bi-brightness-high', theme === 'light');
        document.querySelector('#themeToggle i')?.classList
            .toggle('bi-moon-stars', theme !== 'light');
    };

    // inicial
    if (saved) {
        setTheme(saved);
    } else {
        const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
        setTheme(prefersDark ? 'dark' : 'light');
    }

    // handler
    document.getElementById('themeToggle')?.addEventListener('click', () => {
        setTheme(root.getAttribute('data-bs-theme') === 'light' ? 'dark' : 'light');
    });
})();

// ===== Modal de confirmação para forms com data-confirm =====
(function () {
    const modalEl = document.getElementById('confirmModal');
    if (!modalEl) return;

    const modal = new bootstrap.Modal(modalEl);
    let pendingForm = null;

    document.addEventListener('submit', (ev) => {
        const form = ev.target;
        if (form.matches('form[data-confirm]')) {
            ev.preventDefault();
            pendingForm = form;
            modalEl.querySelector('.modal-body').textContent = form.getAttribute('data-confirm') || 'Confirmar?';
            modal.show();
        }
    });

    document.getElementById('confirmYes')?.addEventListener('click', () => {
        if (pendingForm) pendingForm.submit();
    });
})();

// ===== Auto-exibir toasts (flash) =====
(function () {
    document.querySelectorAll('.toast').forEach(t => new bootstrap.Toast(t).show());
})();


// Auto-dismiss de alertas .alert[data-autohide]
document.querySelectorAll('.alert[data-autohide]').forEach(el => {
    const ms = parseInt(el.dataset.autohide, 10) || 3500;
    setTimeout(() => {
        el.classList.add('fade');
        setTimeout(() => el.remove(), 200);
    }, ms);
});
